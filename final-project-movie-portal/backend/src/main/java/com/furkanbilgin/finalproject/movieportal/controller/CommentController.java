package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.social.CommentDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.comment.UserCommentDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.comment.UserDeleteCommentDTO;
import com.furkanbilgin.finalproject.movieportal.service.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/movie/{movieId}")
  public ResponseEntity<List<CommentDTO>> getCommentsByMovie(@PathVariable Long movieId) {
    return ResponseEntity.ok(commentService.getCommentsByMovie(movieId));
  }

  @PostMapping("/movie/{movieId}")
  public ResponseEntity<CommentDTO> addComment(
      @PathVariable Long movieId, @Valid @RequestBody UserCommentDTO userCommentDTO) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return ResponseEntity.ok(commentService.addComment(username, movieId, userCommentDTO));
  }

  @PreAuthorize(
      "hasAuthority('ADMIN') or @commentService.isCommentOwner(#userDeleteCommentDTO.commentId, authentication.name)")
  @DeleteMapping("/")
  public ResponseEntity<Void> deleteComment(
      @Valid @RequestBody UserDeleteCommentDTO userDeleteCommentDTO) {
    commentService.deleteComment(userDeleteCommentDTO.getCommentId());
    return ResponseEntity.noContent().build();
  }
}
