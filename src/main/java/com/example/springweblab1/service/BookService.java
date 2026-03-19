package com.example.springweblab1.service;

import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.entity.Book;
import com.example.springweblab1.exception.ResourceNotFoundException;
import com.example.springweblab1.mapper.BookMapper;
import com.example.springweblab1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAll(){
        return  bookRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .toList();
    }

    public List<BookDTO> searchBooks(String search){
        if (search == null || search.isBlank()) {
            return getAll();
        }
        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search)
                .stream()
                .map(BookMapper::toDTO)
                .toList();
    }

    public void createBook(CreateBookDTO dto){
       Book book = BookMapper.toEntity(dto);
       bookRepository.save(book);
    }

    public void updateBook(UpdateBookDTO dto){
        Book book = bookRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        BookMapper.updateEntity(dto, book);

        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public BookDTO getById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return BookMapper.toDTO(book);
    }
}
