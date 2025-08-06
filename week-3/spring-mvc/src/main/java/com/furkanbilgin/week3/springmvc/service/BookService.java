package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book createBook(Book book);

    Optional<Book> updateBook(Long id, Book book);

    boolean deleteBook(Long id);
}
