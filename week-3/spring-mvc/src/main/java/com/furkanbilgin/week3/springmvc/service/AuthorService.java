package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    Author createAuthor(Author author);

    Optional<Author> updateAuthor(Long id, Author author);

    boolean deleteAuthor(Long id);
}
