package com.furkanbilgin.week3.springmvc.exception;

import lombok.Getter;

public class BookNotFoundException extends Exception {
    @Getter private final int bookId;

    public BookNotFoundException(int bookId) {
        this.bookId = bookId;
    }
}
