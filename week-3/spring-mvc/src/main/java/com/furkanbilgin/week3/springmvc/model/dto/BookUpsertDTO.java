package com.furkanbilgin.week3.springmvc.model.dto;

import lombok.Data;

@Data
public class BookUpsertDTO {
    private String title;
    private Integer pageCount;
    private Long authorId;
}
