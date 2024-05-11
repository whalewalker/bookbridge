package com.bookbridge.data.repo;

import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.repo.contract.IBorrowedBookRepo;
import com.bookbridge.data.repo.contract.RelationalBaseRepo;
import com.bookbridge.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowedBookRepo extends RelationalBaseRepo<BorrowedBook, IBorrowedBookRepo> {
    private final IBorrowedBookRepo iBorrowedBookRepo;

    public BorrowedBookRepo(IBorrowedBookRepo iBorrowedBookRepo) {
        super(iBorrowedBookRepo, "Borrowed Book");
        this.iBorrowedBookRepo = iBorrowedBookRepo;
    }

    public BorrowedBook getBorrowedBook(Long bookId, Long patronId) {
        Optional<BorrowedBook> borrowedBook = iBorrowedBookRepo.findBorrowedBook(bookId, patronId);
        return borrowedBook.orElseThrow(() -> new ResourceNotFoundException("No active borrowing record found for the given book and patron"));
    }
}
