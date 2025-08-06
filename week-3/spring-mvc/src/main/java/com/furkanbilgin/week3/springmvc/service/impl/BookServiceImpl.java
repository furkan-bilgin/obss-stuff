package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.model.Book;
import com.furkanbilgin.week3.springmvc.repository.BookRepository;
import com.furkanbilgin.week3.springmvc.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(Long id, Book book) {
        return bookRepository
                .findById(id)
                .map(
                        existingBook -> {
                            existingBook.setTitle(book.getTitle());
                            existingBook.setAuthor(book.getAuthor());
                            return bookRepository.save(existingBook);
                        });
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
