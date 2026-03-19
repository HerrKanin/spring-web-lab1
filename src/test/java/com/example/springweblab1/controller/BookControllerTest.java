package com.example.springweblab1.controller;

import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void shouldReturnListView() throws Exception {
        when(bookService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    void shouldReturnCreateView() throws Exception {
        mockMvc.perform(get("/books/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/create"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void shouldCreateBookAndRedirect() throws Exception {
        mockMvc.perform(post("/books/create")
                        .param("title", "Test Book")
                        .param("description", "Test Description")
                        .param("publishedDate", "2020-01-01")
                        .param("author", "Test Author")
                        .param("pages", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        verify(bookService).createBook(any(CreateBookDTO.class));
    }

    @Test
    void shouldReturnCreateViewWhenValidationFails() throws Exception {
        mockMvc.perform(post("/books/create")
                        .param("title", "")
                        .param("description", "")
                        .param("publishedDate", "")
                        .param("author", "")
                        .param("pages", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("books/create"))
                .andExpect(model().hasErrors());

        verify(bookService, never()).createBook(any());
    }

    @Test
    void shouldDeleteBookAndRedirect() throws Exception {
        mockMvc.perform(post("/books/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
        verify(bookService).deleteBook(1L);
    }

    @Test
    void shouldReturnEditView() throws Exception {
        BookDTO book = new BookDTO();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setDescription("Test Description");
        book.setPublishedDate(LocalDate.of(2020, 1, 1));
        book.setAuthor("Test Author");
        book.setPages(100);

        when(bookService.getById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void shouldUpdateBookAndRedirect() throws Exception {
        mockMvc.perform(post("/books/edit")
                        .param("id", "1")
                        .param("title", "Updated Title")
                        .param("description", "Updated Description")
                        .param("publishedDate", "2020-01-01")
                        .param("author", "Updated Author")
                        .param("pages", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        verify(bookService).updateBook(any(UpdateBookDTO.class));
    }

    @Test
    void shouldReturnEditViewWhenUpdateFailsValidation() throws Exception {
        mockMvc.perform(post("/books/edit")
                        .param("id", "1L")
                .param("title", "")
                .param("description", "")
                .param("publishedDate", "")
                .param("author", "")
                .param("pages", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"))
                .andExpect(model().hasErrors());

        verify(bookService, never()).updateBook(any());
    }


}
