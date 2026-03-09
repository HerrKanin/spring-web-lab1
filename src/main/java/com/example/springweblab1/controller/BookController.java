package com.example.springweblab1.controller;

import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model){
        model.addAttribute("books", bookService.getAll());
        return "books/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("book", new CreateBookDTO());
        return "books/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") CreateBookDTO dto){
        bookService.createBook(dto);
        return "redirect:/books";
    }

}
