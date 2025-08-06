package com.furkanbilgin.week3.springmvc.repository;

import com.furkanbilgin.week3.springmvc.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
