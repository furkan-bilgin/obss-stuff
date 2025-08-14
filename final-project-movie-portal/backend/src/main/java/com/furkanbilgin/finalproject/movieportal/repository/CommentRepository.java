package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.social.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query("SELECT c FROM Comment c WHERE c.parent.id = ?1")
  List<Comment> findAllByParentId(Long parentId);
}
