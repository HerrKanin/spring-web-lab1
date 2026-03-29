package com.example.springweblab1.service;

import com.example.springweblab1.dto.BookDTO;
import com.example.springweblab1.dto.CreateBookDTO;
import com.example.springweblab1.dto.UpdateBookDTO;
import com.example.springweblab1.entity.Book;
import com.example.springweblab1.exception.ResourceNotFoundException;
import com.example.springweblab1.mapper.BookMapper;
import com.example.springweblab1.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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

    public Page<BookDTO> searchBooks(String search, int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> booksPage;

        if (search == null || search.isBlank()) {
            booksPage = bookRepository.findAll(pageable);
        } else {
            booksPage = bookRepository
                    .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search, pageable);

        }
        return booksPage.map(BookMapper::toDTO);
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
