package org.eqdev.controller;

import org.eqdev.model.Author;
import org.eqdev.model.Book;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;


@Controller
public class BookController {

        private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @QueryMapping
    public Book bookById(@Argument String id) {
        logger.info("Fetching book with id: {}", id);
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}