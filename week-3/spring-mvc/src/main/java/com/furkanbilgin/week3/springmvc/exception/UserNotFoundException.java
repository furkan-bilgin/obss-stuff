package com.furkanbilgin.week3.springmvc.exception;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super(id, UserNotFoundException.class);
    }
}
