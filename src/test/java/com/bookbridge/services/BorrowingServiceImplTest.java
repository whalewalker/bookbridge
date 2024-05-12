package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.repo.BorrowedBookRepo;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceImplTest {

    @Mock
    private BookRepo bookRepo;

    @Mock
    private PatronRepo patronRepo;

    @Mock
    private BorrowedBookRepo borrowedBookRepo;

    private BorrowingServiceImpl borrowingService;

    @BeforeEach
    void setUp() {
        borrowingService = new BorrowingServiceImpl(bookRepo, patronRepo, borrowedBookRepo);
    }

    @Test
    void testBorrowBook() {
        Book book = new Book();
        Patron patron = new Patron();
        BorrowedBook borrowedBook = new BorrowedBook();
        when(bookRepo.getById(1L)).thenReturn(book);
        when(patronRepo.getById(2L)).thenReturn(patron);
        when(borrowedBookRepo.create(any(BorrowedBook.class))).thenReturn(borrowedBook);

        Response<BorrowedBook> response = borrowingService.borrowBook(1L, 2L);
        assertNotNull(response);
        assertEquals(List.of(borrowedBook), response.getModelList());
        assertNotNull(response.getModelList().get(0).getBorrowedDate());
    }

    @Test
    void testBorrowBookWhenBookNotFound() {
        when(bookRepo.getById(1L)).thenReturn(null);

        Response<BorrowedBook> response = borrowingService.borrowBook(1L, 2L);
        assertNotNull(response);
        assertEquals(List.of(), response.getModelList());
    }

    @Test
    void testBorrowBookWhenPatronNotFound() {
        Book book = new Book();
        when(bookRepo.getById(1L)).thenReturn(book);
        when(patronRepo.getById(2L)).thenReturn(null);

        Response<BorrowedBook> response = borrowingService.borrowBook(1L, 2L);
        assertNotNull(response);
        assertEquals(List.of(), response.getModelList());
    }

    @Test
    void testReturnBook() {
        BorrowedBook borrowedBook = new BorrowedBook();
        when(borrowedBookRepo.getBorrowedBook(1L, 2L)).thenReturn(borrowedBook);
        when(borrowedBookRepo.update(any(BorrowedBook.class))).thenReturn(borrowedBook);

        Response<BorrowedBook> response = borrowingService.returnBook(1L, 2L);
        assertNotNull(response);
        assertEquals(List.of(borrowedBook), response.getModelList());
        assertNotNull(response.getModelList().get(0).getReturnedDate());
    }

    @Test
    void testReturnBookWhenBorrowedBookNotFound() {
        when(borrowedBookRepo.getBorrowedBook(1L, 2L)).thenReturn(null);

        Response<BorrowedBook> response = borrowingService.returnBook(1L, 2L);
        assertNotNull(response);
        assertEquals(List.of(), response.getModelList());
    }
}