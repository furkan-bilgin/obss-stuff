package com.furkanbilgin.week3.springmvc.repository;

import com.furkanbilgin.week3.springmvc.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
