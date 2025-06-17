package com.java.project;


import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import domainLayer.entities.Author;
import domainLayer.entities.Book;
import domainLayer.repositoty.AuthorCrudRepository;
import domainLayer.repositoty.AuthorRepository;
import domainLayer.repositoty.BookCrudRepository;
import domainLayer.repositoty.BookRepository;

@SpringBootTest
public class DomainLayerTests {

    @Autowired
    private BookRepository BookRepository;

    @Autowired
    private BookCrudRepository  BookCrudRepository;

    @Autowired
    private AuthorRepository AuthorRepository; 

    @Autowired
    private AuthorCrudRepository  authorCrudRepository;

    @Test
    void testGetBooks() {
        List<Book> books = BookRepository.findAll();  

        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isGreaterThan(0);

        Book firstBook = books.get(0);
        Assertions.assertThat(firstBook).isNotNull();
        Assertions.assertThat(firstBook.getTitle()).isNotNull().isNotEmpty();

        Author author = firstBook.getAuthor();
        Assertions.assertThat(author).isNotNull();
        Assertions.assertThat(author.getName()).isNotNull().isNotEmpty();
    }

    @Test 
    void testGetBooksCrud() {
        List<Book> books = new ArrayList<>();
        BookCrudRepository.findAll().forEach(books::add);

        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testGetAuthors() {
        List<Author> authors = AuthorRepository.findAll();  

        Assertions.assertThat(authors).isNotNull();
        Assertions.assertThat(authors.size()).isGreaterThan(0);

        Author firstAuthor = authors.get(0);
        Assertions.assertThat(firstAuthor).isNotNull();
        Assertions.assertThat(firstAuthor.getName()).isNotNull().isNotEmpty();
    }

    @Test
    void testGetAuthorsCrud() {
        List<Author> authors = new ArrayList<>(); 
        authorCrudRepository.findAll().forEach(authors::add);

        Assertions.assertThat(authors).isNotNull();
        Assertions.assertThat(authors.size()).isGreaterThan(0); 
    }
}

