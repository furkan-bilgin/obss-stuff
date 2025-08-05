package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.exception.BookNotFoundException;
import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;

import jakarta.validation.Valid;

import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface BookService {
    BookDTO addBook(@Valid BookDTO bookDTO);

    BookDTO getBookById(int id) throws BookNotFoundException;

    BookDTO updateBook(int id, @Valid BookDTO bookDTO) throws BookNotFoundException;

    void deleteBook(int id) throws BookNotFoundException;

    List<BookDTO> getBooks();
}
