package com.furkanbilgin.week3.springmvc.model;

import lombok.Data;

@Data
public class BookSearchRequest {
    private String title;
    private Integer minPageCount;
    private Integer maxPageCount;
    private String authorName;
}
