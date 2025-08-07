package com.furkanbilgin.week3.springmvc.controller;

import com.furkanbilgin.week3.springmvc.model.Book;
import com.furkanbilgin.week3.springmvc.model.BookSearchRequest;
import com.furkanbilgin.week3.springmvc.model.dto.BookUpsertDTO;
import com.furkanbilgin.week3.springmvc.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService
                .getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Book createBook(@RequestBody BookUpsertDTO bookUpsertDTO) {
        return bookService.createBook(bookUpsertDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService
                .updateBook(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestBody BookSearchRequest bookSearchRequest) {
        return ResponseEntity.ok(bookService.searchBooks(bookSearchRequest));
    }
}
