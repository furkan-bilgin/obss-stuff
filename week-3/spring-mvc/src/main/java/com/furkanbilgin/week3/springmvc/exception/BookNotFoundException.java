package com.furkanbilgin.week3.springmvc.exception;

public class BookNotFoundException extends EntityNotFoundException {
    public BookNotFoundException(Long id) {
        super(id, BookNotFoundException.class);
    }
}
