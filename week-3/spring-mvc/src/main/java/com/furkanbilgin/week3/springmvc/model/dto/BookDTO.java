package com.furkanbilgin.week3.springmvc.model.dto;

import lombok.Data;
import lombok.NonNull;

public @Data class BookDTO {
    @NonNull
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String author;
}
