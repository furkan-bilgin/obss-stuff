package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserMovieFavoriteResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserMovieWatchlistResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserUpdateMeDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.favorite.UserFavoriteMovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.favorite.UserUnfavoriteMovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.watchlist.UserUnwatchlistMovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.watchlist.UserWatchlistMovieDTO;
import com.furkanbilgin.finalproject.movieportal.exception.UserNotFoundException;
import com.furkanbilgin.finalproject.movieportal.security.CurrentUserProvider;
import com.furkanbilgin.finalproject.movieportal.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final CurrentUserProvider currentUserProvider;

  @Autowired
  public UserController(UserService userService, CurrentUserProvider currentUserProvider) {
    this.userService = userService;
    this.currentUserProvider = currentUserProvider;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.findAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    return returnUserResponse(userService.findUserById(id));
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
    return returnUserResponse(userService.findUserByUsername(username));
  }

  @GetMapping("/username/{username}/watchlist")
  public ResponseEntity<UserMovieWatchlistResponseDTO> getUserWatchlist(
      @PathVariable String username) {
    var user = userService.findUserByUsername(username);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    return userService
        .getUserWatchlist(user.get().getId())
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/username/{username}/favorites")
  public ResponseEntity<UserMovieFavoriteResponseDTO> getUserFavorites(
      @PathVariable String username) {
    var user = userService.findUserByUsername(username);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    return userService
        .getUserMovieFavorites(user.get().getId())
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(
      @PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
    return returnUserResponse(userService.updateUser(id, userDTO));
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
    return returnUserResponse(userService.deleteUser(id));
  }

  @GetMapping("/me")
  public ResponseEntity<UserDTO> getMe() {
    return ResponseEntity.ok(currentUserProvider.getCurrentUser());
  }

  @PutMapping("/me")
  public ResponseEntity<UserDTO> updateMe(@Valid @RequestBody UserUpdateMeDTO userUpdateMeDTO) {
    var currentUser = currentUserProvider.getCurrentUser();
    return returnUserResponse(userService.updateUser(currentUser.getId(), userUpdateMeDTO));
  }

  @PutMapping("/movie/favorite")
  public void favoriteMovie(@Valid @RequestBody UserFavoriteMovieDTO userFavoriteMovieDTO) {
    userService.favoriteMovie(
        currentUserProvider.getCurrentUser().getId(), userFavoriteMovieDTO.getMovieId());
  }

  @DeleteMapping("/movie/favorite")
  public void unfavoriteMovie(@Valid @RequestBody UserUnfavoriteMovieDTO userUnfavoriteMovieDTO) {
    userService.unfavoriteMovie(
        currentUserProvider.getCurrentUser().getId(), userUnfavoriteMovieDTO.getMovieId());
  }

  @PutMapping("/movie/watchlist")
  public void addToWatchlist(@Valid @RequestBody UserWatchlistMovieDTO userWatchlistMovieDTO) {
    userService.addMovieToWatchlist(
        currentUserProvider.getCurrentUser().getId(), userWatchlistMovieDTO.getMovieId());
  }

  @DeleteMapping("/movie/watchlist")
  public void removeFromWatchlist(
      @Valid @RequestBody UserUnwatchlistMovieDTO userUnwatchlistMovieDTO) {
    userService.removeMovieFromWatchlist(
        currentUserProvider.getCurrentUser().getId(), userUnwatchlistMovieDTO.getMovieId());
  }

  private ResponseEntity<UserDTO> returnUserResponse(Optional<UserDTO> userDTO) {
    return userDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
