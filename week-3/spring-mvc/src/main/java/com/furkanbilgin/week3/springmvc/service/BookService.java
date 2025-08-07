package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.model.Book;
import com.furkanbilgin.week3.springmvc.model.BookSearchRequest;
import com.furkanbilgin.week3.springmvc.model.dto.BookUpsertDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book createBook(BookUpsertDTO book);

    Optional<Book> updateBook(Long id, Book book);

    boolean deleteBook(Long id);

    List<Book> searchBooks(BookSearchRequest bookSearchRequest);
}
