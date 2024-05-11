package com.bookbridge.services.contract;

import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.response.Response;

public interface BorrowingService {
    Response<BorrowedBook> borrowBook(Long bookId, Long patronId);

    Response<BorrowedBook> returnBook(Long bookId, Long patronId);
}
