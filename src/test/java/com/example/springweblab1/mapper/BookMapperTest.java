package com.example.springweblab1.mapper;


import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.entity.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookMapperTest {

    @Test
    void toEntity_shouldMapCreateBookDTOToBook(){
        CreateBookDTO dto = new CreateBookDTO();
        dto.setTitle("Test Title");
        dto.setDescription("Test Description");
        dto.setPublishedDate(LocalDate.of(2020,1,1));
        dto.setAuthor("Test Author");
        dto.setPages(200);

        Book book = BookMapper.toEntity(dto);

        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Description", book.getDescription());
        assertEquals(LocalDate.of(2020, 1, 1), book.getPublishedDate());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(200, book.getPages());
    }

    @Test
    void toDTO_ShouldMapBookToBookDTO(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setDescription("A book about writing better code");
        book.setPublishedDate(LocalDate.of(2008, 8, 1));
        book.setAuthor("Edvin Karlsson");
        book.setPages(464);

        BookDTO dto = BookMapper.toDTO(book);

        assertEquals(1L, dto.getId());
        assertEquals("Clean Code", dto.getTitle());
        assertEquals("A book about writing better code", dto.getDescription());
        assertEquals(LocalDate.of(2008, 8 ,1), dto.getPublishedDate());
        assertEquals("Edvin Karlsson", dto.getAuthor());
        assertEquals(464, dto.getPages());
    }

    @Test
    void updateEntity_shouldUpdateBookFields(){
        UpdateBookDTO dto = new UpdateBookDTO();
        dto.setId(1L);
        dto.setTitle("Updated Title");
        dto.setDescription("Updated Description");
        dto.setPublishedDate(LocalDate.of(2021, 5, 10));
        dto.setAuthor("Updated Author");
        dto.setPages(321);

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Old Title");
        book.setDescription("Old description");
        book.setPublishedDate(LocalDate.of(2000, 1, 1));
        book.setAuthor("Old Author");
        book.setPages(100);

        BookMapper.updateEntity(dto, book);

        assertEquals(1L, book.getId());
        assertEquals("Updated Title", book.getTitle());
        assertEquals("Updated Description", book.getDescription());
        assertEquals(LocalDate.of(2021, 5, 10), book.getPublishedDate());
        assertEquals("Updated Author", book.getAuthor());
        assertEquals(321, book.getPages());
    }
}
