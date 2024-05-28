package com.bookbridge.services;


import com.bookbridge.data.model.Book;
import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.repo.BorrowedBookRepo;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.response.Response;
import com.bookbridge.exception.RequestIsBadException;
import com.bookbridge.exception.ResourceNotFoundException;
import com.bookbridge.services.contract.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BookRepo bookRepo;

    private final PatronRepo patronRepo;

    private final BorrowedBookRepo borrowedBookRepo;

    public Response<BorrowedBook> borrowBook(Long bookId, Long patronId) {
        BorrowedBook existingBorrowedBook = borrowedBookRepo.getBorrowedBook(bookId, patronId);
        if (existingBorrowedBook != null) {
            throw new RequestIsBadException("Borrowed book record already exist");
        }
        Book book = bookRepo.getById(bookId);
        Patron patron = patronRepo.getById(patronId);

        BorrowedBook borrowedBook = BorrowedBook.builder()
                .book(book)
                .patron(patron)
                .borrowedDate(LocalDate.now())
                .build();
        return successfulResponse(List.of(borrowedBookRepo.create(borrowedBook)));
    }

    public Response<BorrowedBook> returnBook(Long bookId, Long patronId) {
        BorrowedBook borrowedBook = borrowedBookRepo.getBorrowedBook(bookId, patronId);

        if (borrowedBook == null) {
            throw new ResourceNotFoundException("No active borrowing record found for the given book and patron");
        }

        borrowedBook.setReturnedDate(LocalDate.now());
        return successfulResponse(List.of(borrowedBookRepo.update(borrowedBook)));
    }
}