package com.bookbridge.controller.v1;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.security.JwtUtils;
import com.bookbridge.security.UserDetailsServiceImpl;
import com.bookbridge.services.contract.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@WithMockUser
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(new Book(), new Book());
        Response<Book> response = successfulResponse(books);
        when(bookService.getAll()).thenReturn(response);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(2));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book();
        Response<Book> response = successfulResponse(List.of(book));
        when(bookService.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book();
        Response<Book> response = successfulResponse(List.of(book));
        when(bookService.create(any(BookRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book();
        Response<Book> response = successfulResponse(List.of(book));
        when(bookService.update(eq(1L), any(BookRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testDeleteBook() throws Exception {
        when(bookService.delete(1L)).thenReturn(any(Response.class));

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
    }
}