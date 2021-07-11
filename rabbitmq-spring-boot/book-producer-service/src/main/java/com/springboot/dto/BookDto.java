package com.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = BookDto.class)
@Getter
@Setter
@NoArgsConstructor
public class BookDto implements Serializable {

    @NotEmpty(message = "ISBN is required")
    private String isbn;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Author is required")
    private String author;

    @NotNull(message = "Price is required")
    private int price;

    @NotEmpty(message = "Publisher is required")
    private String publisher;

    @NotEmpty(message = "Genre is required")
    private String genre;

    @NotEmpty(message = "Description is required")
    private String description;

    public BookDto(String isbn, String title, String author, int price, String publisher, String genre, String description ) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.genre = genre;
        this.description = description;
    }
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
