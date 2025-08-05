package com.furkanbilgin.week3.springmvc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

public @Data @AllArgsConstructor class BookDTO {
    private Integer id;
    @NonNull private String title;
    @NonNull private String author;
}
