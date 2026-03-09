package com.example.springweblab1.mapper;

import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.entity.Book;
import org.hibernate.sql.Update;

public class BookMapper {

    public static Book toEntity(CreateBookDTO dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPublishedDate(dto.getPublishedDate());
        book.setAuthor(dto.getAuthor());
        book.setPages(dto.getPages());

        return book;
    }

    public static BookDTO toDTO(Book book){
        BookDTO dto = new BookDTO();

        dto.setId((book.getId()));
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setPublishedDate(book.getPublishedDate());
        dto.setAuthor(book.getAuthor());
        dto.setPages(book.getPages());

        return dto;
    }

    public static void updateEntity(UpdateBookDTO dto, Book book){

        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPublishedDate(dto.getPublishedDate());
        book.setAuthor(dto.getAuthor());
        book.setPages(dto.getPages());
    }
}
