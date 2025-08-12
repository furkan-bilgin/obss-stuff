package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserMovieFavoriteResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserMovieWatchlistResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.favorite.UserFavoriteMovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.favorite.UserUnfavoriteMovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.watchlist.UserUnwatchlistMovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.watchlist.UserWatchlistMovieDTO;
import com.furkanbilgin.finalproject.movieportal.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
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

  @GetMapping("/{id}/watchlist")
  public ResponseEntity<UserMovieWatchlistResponseDTO> getUserWatchlist(@PathVariable Long id) {
    var user = getCurrentUser();
    if (!user.getId().equals(id)) { // TODO: add an option to toggle a public/private profile
      throw new AccessDeniedException("Cannot access another user's watchlist");
    }
    return ResponseEntity.ok(userService.getUserWatchlist(id));
  }

  @GetMapping("/{id}/favorites")
  public ResponseEntity<UserMovieFavoriteResponseDTO> getUserFavorites(@PathVariable Long id) {
    var user = getCurrentUser();
    if (!user.getId().equals(id)) { // TODO: add an option to toggle a public/private profile
      throw new AccessDeniedException("Cannot access another user's watchlist");
    }
    return ResponseEntity.ok(userService.getUserMovieFavorites(id));
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
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return returnUserResponse(userService.findUserByUsername(username));
  }

  @PutMapping("/movie/favorite")
  public void favoriteMovie(@Valid @RequestBody UserFavoriteMovieDTO userFavoriteMovieDTO) {
    userService.favoriteMovie(
        getCurrentUser().getId(),
        userFavoriteMovieDTO.getMovieId(),
        userFavoriteMovieDTO.getScore());
  }

  @DeleteMapping("/movie/favorite")
  public void unfavoriteMovie(@Valid @RequestBody UserUnfavoriteMovieDTO userUnfavoriteMovieDTO) {
    userService.unfavoriteMovie(getCurrentUser().getId(), userUnfavoriteMovieDTO.getMovieId());
  }

  @PutMapping("/movie/watchlist")
  public void addToWatchlist(@Valid @RequestBody UserWatchlistMovieDTO userWatchlistMovieDTO) {
    userService.addMovieToWatchlist(getCurrentUser().getId(), userWatchlistMovieDTO.getMovieId());
  }

  @DeleteMapping("/movie/watchlist")
  public void removeFromWatchlist(
      @Valid @RequestBody UserUnwatchlistMovieDTO userUnwatchlistMovieDTO) {
    userService.removeMovieFromWatchlist(
        getCurrentUser().getId(), userUnwatchlistMovieDTO.getMovieId());
  }

  private UserDTO getCurrentUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    // Probably don't even need this because security filter handles this
    // before it even reaches us, but well...
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new AccessDeniedException("Not authorized");
    }
    var username = authentication.getName();
    var user = userService.findUserByUsername(username);
    if (user.isEmpty()) {
      throw new AccessDeniedException("User not found");
    }
    return user.get();
  }

  private ResponseEntity<UserDTO> returnUserResponse(Optional<UserDTO> userDTO) {
    return userDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
