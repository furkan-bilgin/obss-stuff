package com.furkanbilgin.week3.springmvc.controller;

import com.furkanbilgin.week3.springmvc.exception.BookNotFoundException;
import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;
import com.furkanbilgin.week3.springmvc.service.BookService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public BookDTO getBookById(@PathVariable int id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDTO addBook(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable int id, @RequestBody @Valid BookDTO bookDTO)
            throws BookNotFoundException {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }
}
