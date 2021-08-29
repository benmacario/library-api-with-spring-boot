package com.workspacespringrest.libraryapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workspacespringrest.libraryapi.controller.dto.BookDTO;
import com.workspacespringrest.libraryapi.model.Book;
import com.workspacespringrest.libraryapi.services.BookServices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
  static String BOOK_API = "/v1/api/books/";

  @Autowired
  MockMvc mvc;

  @MockBean
  BookServices services;

  @Test
  @DisplayName("Successful book creation.")
  public void createBookTest() throws Exception {
    BookDTO book = BookDTO.builder().author("Jonas").title("Estrutura de Código").isbn("001").build();
    Book savedBook = Book.builder().id(101l).author("Jonas").title("Estrutura de Código").isbn("001").build();

    BDDMockito.given(services.save(Mockito.any(Book.class))).willReturn(savedBook);
    String json = new ObjectMapper().writeValueAsString(book);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BOOK_API)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

    mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("title").value(book.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("author").value(book.getAuthor()))
        .andExpect(MockMvcResultMatchers.jsonPath("isbn").value(book.getIsbn()));
  }
}