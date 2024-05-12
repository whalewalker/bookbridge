package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepo bookRepo;

    @Mock
    private ModelMapper mapper;

    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepo, mapper);
    }

    @Test
    void testGetAll() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookRepo.getAll()).thenReturn(books);

        Response<Book> response = bookService.getAll();
        assertNotNull(response);
        assertEquals(books, response.getModelList());
    }

    @Test
    void testGetById() {
        Book book = new Book();
        when(bookRepo.getById(1L)).thenReturn(book);

        Response<Book> response = bookService.getById(1L);
        assertNotNull(response);
        assertEquals(List.of(book), response.getModelList());
    }

    @Test
    void testCreate() {
        Book book = new Book();
        BookRequest request = new BookRequest();
        when(mapper.map(request, Book.class)).thenReturn(book);
        when(bookRepo.create(book)).thenReturn(book);

        Response<Book> response = bookService.create(request);
        assertNotNull(response);
        assertEquals(List.of(book), response.getModelList());
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        BookRequest request = new BookRequest();
        when(bookRepo.getById(1L)).thenReturn(book);
        when(bookRepo.update(any(Book.class))).thenReturn(book);

        Response<Book> response = bookService.update(1L, request);
        assertNotNull(response);
        assertEquals(List.of(book), response.getModelList());
        verify(mapper, times(1)).map(request, book);
    }

    @Test
    void testDelete() {
        Response<?> response = bookService.delete(1L);
        assertNotNull(response);
        verify(bookRepo, times(1)).delete(1L);
    }
}