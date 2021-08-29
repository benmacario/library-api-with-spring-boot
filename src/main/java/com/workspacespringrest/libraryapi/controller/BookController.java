package com.workspacespringrest.libraryapi.controller;

import com.workspacespringrest.libraryapi.controller.dto.BookDTO;
import com.workspacespringrest.libraryapi.model.Book;
import com.workspacespringrest.libraryapi.services.BookServices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/books")
public class BookController {
  private BookServices service;

  public BookController(BookServices service) {
    this.service = service;
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public BookDTO create(@RequestBody BookDTO book) {
    Book entity = Book.builder().author(book.getAuthor()).title(book.getTitle()).isbn(book.getIsbn()).build();
    entity = service.save(entity);

    return BookDTO.builder().id(entity.getId()).author(entity.getAuthor()).title(entity.getTitle())
        .isbn(entity.getIsbn()).build();
  }
}
