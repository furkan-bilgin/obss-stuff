package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);
    BookDTO getBookById(int id);
    BookDTO updateBook(int id, BookDTO bookDTO);
    void deleteBook(int id);
    List<BookDTO> getBooks();
}
