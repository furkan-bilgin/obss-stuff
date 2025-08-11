package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.register.RegisterRequestDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
  UserDTO saveUser(RegisterRequestDTO registerUserDTO);

  List<UserDTO> findAllUsers();

  Optional<UserDTO> findUserById(Long id);

  Optional<UserDTO> findUserByUsername(String username);

  Optional<UserDTO> updateUser(Long id, UserDTO userDTO);

  String updateUserToken(Long id);

  Optional<UserDTO> deleteUser(Long id);

  void favoriteMovie(Long id, Long movieId, Long score);

  void unfavoriteMovie(Long id, Long movieId);

  void addMovieToWatchlist(Long id, Long movieId);

  void removeMovieFromWatchlist(Long id, Long movieId);
}
