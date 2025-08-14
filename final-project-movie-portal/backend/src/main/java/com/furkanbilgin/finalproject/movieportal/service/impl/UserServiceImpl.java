package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.config.PasswordHasher;
import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserMovieFavoriteResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserMovieWatchlistResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserUpdateMeDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.register.RegisterRequestDTO;
import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import com.furkanbilgin.finalproject.movieportal.model.user.User;
import com.furkanbilgin.finalproject.movieportal.model.user.UserMovieFavorite;
import com.furkanbilgin.finalproject.movieportal.model.user.UserMovieWatchlist;
import com.furkanbilgin.finalproject.movieportal.repository.MovieRepository;
import com.furkanbilgin.finalproject.movieportal.repository.RoleRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserMovieFavoriteRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserMovieWatchlistRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import com.furkanbilgin.finalproject.movieportal.service.JWTService;
import com.furkanbilgin.finalproject.movieportal.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final JWTService jwtService;
  private final Long jwtTtl;
  private final PasswordHasher passwordHasher;
  private final MovieRepository movieRepository;
  private final UserMovieFavoriteRepository userMovieFavoriteRepository;
  private final UserMovieWatchlistRepository userMovieWatchlistRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      ModelMapper modelMapper,
      JWTService jwtService,
      @Value("${application.security.jwt-ttl}") Long jwtTtl,
      PasswordHasher passwordHasher,
      MovieRepository movieRepository,
      UserMovieFavoriteRepository userMovieFavoriteRepository,
      UserMovieWatchlistRepository userMovieWatchlistRepository,
      RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
    this.jwtService = jwtService;
    this.jwtTtl = jwtTtl;
    this.passwordHasher = passwordHasher;
    this.movieRepository = movieRepository;
    this.userMovieFavoriteRepository = userMovieFavoriteRepository;
    this.userMovieWatchlistRepository = userMovieWatchlistRepository;
    this.roleRepository = roleRepository;
  }

  public UserDTO saveUser(RegisterRequestDTO registerUserDTO) {
    var user = modelMapper.map(registerUserDTO, User.class);
    user.setPassword(passwordHasher.hashPassword(registerUserDTO.getPassword()));
    user.setRoles(new ArrayList<>());
    user.getRoles().add(roleRepository.findByName("USER").orElseThrow());
    // If this is the first user, give them the ADMIN role
    if (userRepository.findAll().isEmpty()) {
      user.getRoles().add(roleRepository.findByName("ADMIN").orElseThrow());
    }
    user = userRepository.save(user);
    return modelMapper.map(user, UserDTO.class);
  }

  public List<UserDTO> findAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> modelMapper.map(user, UserDTO.class))
        .toList();
  }

  public Optional<UserDTO> findUserById(Long id) {
    return userRepository.findById(id).map(user -> modelMapper.map(user, UserDTO.class));
  }

  @Override
  public Optional<UserDTO> findUserByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .map(user -> modelMapper.map(user, UserDTO.class));
  }

  @Override
  public Optional<UserDTO> findUserByEmail(String email) {
    return userRepository.findByEmail(email).map(user -> modelMapper.map(user, UserDTO.class));
  }

  public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
    var userOpt = userRepository.findById(id);
    if (userOpt.isPresent()) {
      var user = userOpt.get();
      user.setUsername(userDTO.getUsername());
      user.setEmail(userDTO.getEmail());
      if (user.getRoles() != null) {
        var newRoles =
            new ArrayList<>(userDTO.getRoles())
                .stream()
                    .map(
                        roleDTO ->
                            roleRepository
                                .findById(roleDTO.getId())
                                .orElseThrow(
                                    () ->
                                        new EntityNotFoundException(
                                            "Role not found: " + roleDTO.getName())))
                    .toList();
        user.getRoles().clear();
        user.getRoles().addAll(newRoles);
      }
      return Optional.of(modelMapper.map(userRepository.save(user), UserDTO.class));
    }
    return Optional.empty();
  }

  @Override
  public Optional<UserDTO> updateUser(Long id, UserUpdateMeDTO userUpdateMeDTO) {
    var userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      return Optional.empty();
    }
    var user = userOpt.get();
    user.setEmail(userUpdateMeDTO.getEmail());
    if (userUpdateMeDTO.getPassword() != null) {
      user.setPassword(passwordHasher.hashPassword(userUpdateMeDTO.getPassword()));
    }
    return Optional.of(modelMapper.map(userRepository.save(user), UserDTO.class));
  }

  @Override
  public Optional<UserMovieFavoriteResponseDTO> getUserMovieFavorites(Long id) {
    var user = userRepository.findById(id);
    if (user.isEmpty()) {
      return Optional.empty();
    }
    // TODO: n+1?
    // TODO: add pagination
    var favorites =
        user.get().getFavorites().stream()
            .map(favorite -> modelMapper.map(favorite.getMovie(), MovieDTO.class))
            .toList();
    return Optional.of(
        new UserMovieFavoriteResponseDTO(modelMapper.map(user, UserDTO.class), favorites));
  }

  @Override
  public Optional<UserMovieWatchlistResponseDTO> getUserWatchlist(Long id) {
    var user = userRepository.findById(id);
    if (user.isEmpty()) {
      return Optional.empty();
    }
    // TODO: n+1?
    // TODO: add pagination
    var watchlist =
        user.get().getWatchlist().stream()
            .map(watchlistItem -> modelMapper.map(watchlistItem.getMovie(), MovieDTO.class))
            .toList();
    return Optional.of(
        new UserMovieWatchlistResponseDTO(modelMapper.map(user, UserDTO.class), watchlist));
  }

  @Override
  public Optional<String> getUserAccessToken(Long id) {
    var user = this.findUserById(id);
    return user.map(userDTO -> jwtService.generate(userDTO.getUsername(), jwtTtl));
  }

  public Optional<UserDTO> deleteUser(Long id) {
    var userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      return Optional.empty();
    }
    userRepository.deleteById(id);
    return Optional.of(modelMapper.map(userOpt, UserDTO.class));
  }

  @Override
  public void favoriteMovie(Long id, Long movieId) {
    var user = findUserByIdOrThrow(id);
    var movie = findMovieByIdOrThrow(movieId);
    var existingFavorite = userMovieFavoriteRepository.findByUserAndMovie(user, movie);
    if (existingFavorite.isPresent()) {
      // Update existing favorite score
      var favorite = existingFavorite.get();
      userMovieFavoriteRepository.save(favorite);
      return;
    }
    var favorite = new UserMovieFavorite();
    favorite.setUser(user);
    favorite.setMovie(movie);

    userMovieFavoriteRepository.save(favorite);
  }

  @Override
  public void unfavoriteMovie(Long id, Long movieId) {
    var user = findUserByIdOrThrow(id);
    var movie = findMovieByIdOrThrow(movieId);
    var favorite =
        userMovieFavoriteRepository
            .findByUserAndMovie(user, movie)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Favorite not found for user " + id + " and movie " + movieId));
    userMovieFavoriteRepository.delete(favorite);
  }

  @Override
  public void addMovieToWatchlist(Long id, Long movieId) {
    var user = findUserByIdOrThrow(id);
    var movie = findMovieByIdOrThrow(movieId);
    var existingWatchlist = userMovieWatchlistRepository.findByUserAndMovie(user, movie);
    if (existingWatchlist.isPresent()) {
      return;
    }
    var watchlist = new UserMovieWatchlist();
    watchlist.setUser(user);
    watchlist.setMovie(movie);

    userMovieWatchlistRepository.save(watchlist);
  }

  @Override
  public void removeMovieFromWatchlist(Long id, Long movieId) {
    var user = findUserByIdOrThrow(id);
    var movie = findMovieByIdOrThrow(movieId);
    var watchlist =
        userMovieWatchlistRepository
            .findByUserAndMovie(user, movie)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Watchlist entry not found for user " + id + " and movie " + movieId));
    userMovieWatchlistRepository.delete(watchlist);
  }

  private User findUserByIdOrThrow(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
  }

  private Movie findMovieByIdOrThrow(Long id) {
    return movieRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Movie not found with id " + id));
  }
}
