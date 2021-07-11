package com.springboot.config;

import com.springboot.dto.BookDto;
import com.springboot.model.Book;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(BookDto.class, Book.class)
                .field("isbn", "isbn")
                .field("title", "title")
                .field("author", "author")
                .field("price", "price")
                .field("publisher", "publisher")
                .field("genre", "genre")
                .field("description", "description")
                .byDefault().register();

    }


}

