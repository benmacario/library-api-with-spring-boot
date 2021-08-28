package com.workspacespringrest.libraryapi.controller;

import com.workspacespringrest.libraryapi.controller.dto.BookDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/books")
public class BookController {

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public BookDTO create(@RequestBody BookDTO book) {
    return book;
  }
}
