package com.springboot.repository;

import com.springboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorContaining(String author);
    List<Book> findByTitleContaining(String title);
}
