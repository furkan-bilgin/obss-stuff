package com.furkanbilgin.finalproject.movieportal.dto.social;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
  private Long id;
  private UserDTO user;
  private MovieDTO movie;
  private List<CommentDTO> children;
  private String content;
}
