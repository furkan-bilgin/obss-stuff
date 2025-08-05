package com.furkanbilgin.week3.springmvc.model;

import lombok.Data;

public @Data class Book {
    private final int id;
    private final String title;
    private final String author;
}
