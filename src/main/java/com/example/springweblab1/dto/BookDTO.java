package com.example.springweblab1.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BookDTO {

    private Long id;
    @Size(max = 50, message = "Title cannot be longer than 100 characters")
    private String title;
    @Size(max = 200, message = "Description cannot be longer than 500 characters")
    private String description;
    private LocalDate publishedDate;
    @Size(max = 50, message = "Author cannot be longer than 100 characters")
    private String author;
    private Integer pages;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
