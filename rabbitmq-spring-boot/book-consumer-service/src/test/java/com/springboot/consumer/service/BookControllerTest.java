package com.springboot.consumer.service;

import com.springboot.controller.BookController;
import com.springboot.model.Book;
import com.springboot.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookRepository bookRepository;

    @Test
    public void testFindAll()
    {
     Book book = new Book(1, "abcd1334",
             "Sherlock Holmes Adventures", "Arthur Conan Doyle",
             10, "George Newnes", "Detective fiction short stories",
             "The Adventures of Sherlock Holmes is a collection of twelve short stories by " +
                     "Arthur Conan Doyle, first published on 14 October 1892");

     Book book2 = new Book(2, "abcdef123", "Last Day", "Luanne Rice", 20,
             "New York Times", "Detective fiction short stories", "From celebrated New York Times bestselling author " +
             "Luanne Rice comes a riveting story of a seaside community shaken by a violent crime and a tragic loss." );

     List<Book> bookList = new ArrayList<>();
     bookList.add(book);
     bookList.add(book2);

     when(bookRepository.findAll()).thenReturn(bookList);

     ResponseEntity<List<Book>> result = bookController.getAllBooks(null);
     assertThat(result.getStatusCodeValue()).isEqualTo(200);
     assertThat(result.getBody().get(0).getTitle()).isEqualTo(book.getTitle());
     assertThat(result.getBody().get(1).getTitle()).isEqualTo(book2.getTitle());


    }

    @Test
    public void testFindAllContainingTitle()
    {
        Book book = new Book(1, "abcd1334",
                "Sherlock Holmes Adventures", "Arthur Conan Doyle",
                10, "George Newnes", "Detective fiction short stories",
                "The Adventures of Sherlock Holmes is a collection of twelve short stories by " +
                        "Arthur Conan Doyle, first published on 14 October 1892");

        Book book2 = new Book(5, "abcdghjjf123", "The Return of Sherlock Holmes", "Arthur Conan Doyle", 20,
                "George Newnes", "Detective fiction short stories", "The Return of Sherlock Holmes is a 1905 collection of 13 Sherlock Holmes stories, originally published in 1903–1904, by Arthur Conan Doyle. " +
                "The stories were published in the Strand Magazine in Britain and Collier's in the United States" );

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);

        when(bookRepository.findByTitleContaining("Sherlock Holmes")).thenReturn(bookList);

        ResponseEntity<List<Book>> result = bookController.getAllBooks("Sherlock Holmes");
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().get(0).getTitle()).isEqualTo(book.getTitle());
        assertThat(result.getBody().get(1).getTitle()).isEqualTo(book2.getTitle());

    }


    @Test
    public void testFindByContainingAuthor() {



        Book book = new Book(2, "abcdef123", "Last Day", "Luanne Rice", 20,
                "New York Times", "Detective fiction short stories", "From celebrated New York Times bestselling author " +
                "Luanne Rice comes a riveting story of a seaside community shaken by a violent crime and a tragic loss." );

        Book book2 = new Book(3, "abcd1334",
                "Perfect summer", "Luanne Rice",
                30, "George Newnes", "Detective fiction short stories",
                "Bay McCabe is looking forward to a perfect summer at the seaside with her " +
                        "family, but when her husband disappears, Bay is forced to face the " +
                        "truth and the interest of an old love");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);

        when(bookRepository.findByAuthorContaining("Luanne")).thenReturn(bookList);

        ResponseEntity<List<Book>> result = bookController.findByAuthor("Luanne");
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().get(0).getTitle()).isEqualTo(book.getTitle());
        assertThat(result.getBody().get(1).getTitle()).isEqualTo(book2.getTitle());




    }








}
