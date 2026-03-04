package com.example.springweblab1.repository;

import com.example.springweblab1.entity.Book;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Long> {
}
