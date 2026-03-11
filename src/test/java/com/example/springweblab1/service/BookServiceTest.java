package com.example.springweblab1.service;

import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.entity.Book;
import com.example.springweblab1.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    void getAll_shouldReturnListOfBookDTO(){

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setDescription("Test Description");
        book.setPublishedDate(LocalDate.of(2020,1,1));
        book.setAuthor("Test Author");
        book.setPages(200);

        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<BookDTO> result = bookService.getAll();

        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());

        verify(bookRepository).findAll();
    }

    @Test
    void getById_shouldReturnBookDTo(){

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setDescription("Test Description");
        book.setPublishedDate(LocalDate.of(2020,1,1));
        book.setAuthor("Test Author");
        book.setPages(200);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO result = bookService.getById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Test Title", result.getTitle());

        verify(bookRepository).findById(1L);
    }

    @Test
    void createBook_shouldSaveBook(){

        CreateBookDTO dto = new CreateBookDTO();
        dto.setTitle("Test Title");
        dto.setDescription("Test Description");
        dto.setPublishedDate(LocalDate.of(2020,1,1));
        dto.setAuthor("Test Author");
        dto.setPages(200);

        bookService.createBook(dto);

        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void deleteBook_shouldCallRepositoryDelete(){

        bookService.deleteBook(1L);

        verify(bookRepository).deleteById(1L);
    }

    @Test
    void updateBook_shouldUpdateExistingBook(){

        UpdateBookDTO dto = new UpdateBookDTO();
        dto.setId(1L);
        dto.setTitle("Updated Title");
        dto.setDescription("Updated Description");
        dto.setPublishedDate(LocalDate.of(2022,1,1));
        dto.setAuthor("Updated Author");
        dto.setPages(300);

        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Old Title");
        existingBook.setDescription("Old Description");
        existingBook.setPublishedDate(LocalDate.of(2000,1,1));
        existingBook.setAuthor("Old Author");
        existingBook.setPages(100);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));

        bookService.updateBook(dto);

        assertEquals("Updated Title", existingBook.getTitle());
        assertEquals("Updated Description", existingBook.getDescription());
        assertEquals(300, existingBook.getPages());

        verify(bookRepository).findById(1L);
        verify(bookRepository).save(existingBook);
    }
}
