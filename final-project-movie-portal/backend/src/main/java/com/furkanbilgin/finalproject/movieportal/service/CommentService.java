package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.social.CommentDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.comment.UserCommentDTO;
import java.util.List;

public interface CommentService {
  List<CommentDTO> getCommentsByMovie(Long movieId);

  CommentDTO addComment(String username, Long movieId, UserCommentDTO userCommentDTO);

  void deleteComment(Long commentId);

  boolean isCommentOwner(Long commentId, String username);
}
