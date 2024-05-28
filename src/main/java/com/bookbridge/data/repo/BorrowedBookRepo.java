package com.bookbridge.data.repo;

import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.repo.contract.IBorrowedBookRepo;
import com.bookbridge.data.repo.contract.RelationalBaseRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowedBookRepo extends RelationalBaseRepo<BorrowedBook, IBorrowedBookRepo> {
    private final IBorrowedBookRepo iBorrowedBookRepo;

    public BorrowedBookRepo(@Lazy IBorrowedBookRepo iBorrowedBookRepo) {
        super(iBorrowedBookRepo, "Borrowed Book");
        this.iBorrowedBookRepo = iBorrowedBookRepo;
    }

    public BorrowedBook getBorrowedBook(Long bookId, Long patronId) {
        Optional<BorrowedBook> borrowedBook = iBorrowedBookRepo.findBorrowedBook(bookId, patronId);
        return borrowedBook.orElse(null);
    }
}
