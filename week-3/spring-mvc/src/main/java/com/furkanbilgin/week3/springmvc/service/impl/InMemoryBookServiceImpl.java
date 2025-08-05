package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.component.BookMapper;
import com.furkanbilgin.week3.springmvc.model.Book;
import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;
import com.furkanbilgin.week3.springmvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InMemoryBookServiceImpl implements BookService {
    private final HashMap<Integer, Book> bookStore = new HashMap<>();
    private final BookMapper bookMapper;

    @Autowired
    private InMemoryBookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        var id = bookStore.size();
        bookStore.put(id, bookMapper.toBook(bookDTO, bookStore.size()));
        return bookDTO;
    }

    @Override
    public BookDTO getBookById(int id) {
        return bookMapper.toBookDTO(bookStore.get(id));
    }

    @Override
    public BookDTO updateBook(int id, BookDTO bookDTO) {
        if (bookStore.containsKey(id)) {
            bookStore.put(id, bookMapper.toBook(bookDTO, id));
            return bookDTO;
        } else {
            throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
        }
    }

    @Override
    public void deleteBook(int id) {
        bookStore.remove(id);
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookStore.values().stream().map(bookMapper::toBookDTO).toList();
    }
}
