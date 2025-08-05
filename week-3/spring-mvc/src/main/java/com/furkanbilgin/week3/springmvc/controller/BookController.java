package com.furkanbilgin.week3.springmvc.controller;

import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;
import com.furkanbilgin.week3.springmvc.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public BookDTO getBookById(@PathVariable int id) {
        try {
            return bookService.getBookById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping
    public BookDTO addBook(@ModelAttribute @Valid BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PatchMapping
    public BookDTO updateBook(@RequestParam int id, @ModelAttribute @Valid BookDTO bookDTO) {
        try {
            return bookService.updateBook(id, bookDTO);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping
    public void deleteBook(@RequestParam int id) {
        bookService.deleteBook(id);
    }
}
