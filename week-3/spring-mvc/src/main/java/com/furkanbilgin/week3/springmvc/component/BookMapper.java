package com.furkanbilgin.week3.springmvc.component;

import com.furkanbilgin.week3.springmvc.model.Book;
import com.furkanbilgin.week3.springmvc.model.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO toBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor());
    }

    public Book toBook(BookDTO bookDTO, int id) {
        return new Book(id, bookDTO.getTitle(), bookDTO.getAuthor());
    }
}
