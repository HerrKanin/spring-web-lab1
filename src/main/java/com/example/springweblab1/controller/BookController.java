package com.example.springweblab1.controller;

import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String search, Model model) {
        model.addAttribute("books", bookService.searchBooks(search));
        model.addAttribute("search", search);
        return "books/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new CreateBookDTO());
        return "books/create";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("book") CreateBookDTO dto, BindingResult result, Model model) {

        if(result.hasErrors()){
            if (result.hasFieldErrors("publishedDate")){
                String code = result.getFieldError("publishedDate").getCode();

                if("typeMismatch".equals(code)) {
                    model.addAttribute("publishedDateError",
                            "Please enter date in format yyyy-MM-dd");
                }
            }
            return "books/create";
        }

        bookService.createBook(dto);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        BookDTO book = bookService.getById(id);

        UpdateBookDTO dto = new UpdateBookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setPublishedDate(book.getPublishedDate());
        dto.setAuthor(book.getAuthor());
        dto.setPages(book.getPages());

        model.addAttribute("book", dto);

        return "books/edit";
    }

    @PostMapping("/edit")
    public String updateBook(@Valid @ModelAttribute("book") UpdateBookDTO dto, BindingResult result, Model model) {

        if (result.hasErrors()){
            if (result.hasFieldErrors("publishedDate")){
                String code = result.getFieldError("publishedDate").getCode();

                if("typeMismatch".equals(code)){
                    model.addAttribute("publishedDateError",
                            "Please enter date in format yyyy-MM-dd");
                }
            }

            return "books/edit";
        }

        bookService.updateBook(dto);
        return "redirect:/books";
    }

}
