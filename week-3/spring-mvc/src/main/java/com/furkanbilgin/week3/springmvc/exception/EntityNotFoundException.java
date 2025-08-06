package com.furkanbilgin.week3.springmvc.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, Class<?> clazz) {
        super(clazz.getName() + " with ID " + id + " not found.");
    }
    
    public EntityNotFoundException(String name, Class<?> clazz) {
        super(clazz.getName() + " with name " + name + " not found.");
    }
}
