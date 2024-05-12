package com.bookbridge.controller.v1;

import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public Callable<ResponseEntity<Response<BorrowedBook>>> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return () -> {
            Response<BorrowedBook> response = borrowingService.borrowBook(bookId, patronId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        };
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public Callable<ResponseEntity<Response<BorrowedBook>>> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return () -> {
            Response<BorrowedBook> response = borrowingService.returnBook(bookId, patronId);
            return ResponseEntity.ok(response);
        };
    }
}