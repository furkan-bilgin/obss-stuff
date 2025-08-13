package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.social.CommentDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.comment.UserCommentDTO;
import com.furkanbilgin.finalproject.movieportal.model.social.Comment;
import com.furkanbilgin.finalproject.movieportal.repository.CommentRepository;
import com.furkanbilgin.finalproject.movieportal.repository.MovieRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final MovieRepository movieRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public CommentServiceImpl(
      CommentRepository commentRepository,
      UserRepository userRepository,
      MovieRepository movieRepository,
      ModelMapper modelMapper) {
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<CommentDTO> getCommentsByMovie(Long movieId) {
    var comments =
        commentRepository.findAll().stream()
            .filter(
                c ->
                    c.getMovie() != null
                        && c.getMovie().getId().equals(movieId)
                        && c.getParent() == null)
            .toList();
    return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).toList();
  }

  @Override
  public CommentDTO addComment(String username, Long movieId, UserCommentDTO userCommentDTO) {
    var userOpt = userRepository.findByUsername(username);
    var movieOpt = movieRepository.findById(movieId);
    if (userOpt.isEmpty() || movieOpt.isEmpty()) {
      throw new RuntimeException("User or Movie not found");
    }
    var comment = new Comment();
    comment.setUser(userOpt.get());
    comment.setMovie(movieOpt.get());
    comment.setContent(userCommentDTO.getContent());
    if (userCommentDTO.getParentCommentId() != null) {
      commentRepository.findById(userCommentDTO.getParentCommentId()).ifPresent(comment::setParent);
    }
    var saved = commentRepository.save(comment);
    return modelMapper.map(saved, CommentDTO.class);
  }

  @Override
  public void deleteComment(Long commentId) {
    commentRepository.deleteById(commentId);
  }

  @Override
  public boolean isCommentOwner(Long commentId, String username) {
    var commentOpt = commentRepository.findById(commentId);
    return commentOpt.isPresent() && commentOpt.get().getUser().getUsername().equals(username);
  }
}
