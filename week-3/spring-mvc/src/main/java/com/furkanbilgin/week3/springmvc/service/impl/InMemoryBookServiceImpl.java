package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.component.BookMapper;
import com.furkanbilgin.week3.springmvc.exception.BookNotFoundException;
import com.furkanbilgin.week3.springmvc.model.Book;
import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;
import com.furkanbilgin.week3.springmvc.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class InMemoryBookServiceImpl implements BookService {
    private final HashMap<Integer, Book> bookStore = new HashMap<>();
    private final BookMapper bookMapper;

    @Autowired
    InMemoryBookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        var id = bookStore.size();
        bookStore.put(id, bookMapper.toBook(bookDTO, bookStore.size()));
        return bookMapper.toBookDTO(bookStore.get(id));
    }

    @Override
    public BookDTO getBookById(int id) throws BookNotFoundException {
        if (!bookStore.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        return bookMapper.toBookDTO(bookStore.get(id));
    }

    @Override
    public BookDTO updateBook(int id, BookDTO bookDTO) throws BookNotFoundException {
        if (bookStore.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        bookStore.put(id, bookMapper.toBook(bookDTO, id));
        return bookDTO;
    }

    @Override
    public void deleteBook(int id) throws BookNotFoundException {
        if (!bookStore.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        bookStore.remove(id);
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookStore.values().stream().map(bookMapper::toBookDTO).toList();
    }
}
