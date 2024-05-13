package com.bookbridge.controller.v1;

import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Borrowing", description = "Operations related to book borrowing and returning")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @Operation(summary = "Borrow a book", responses = {
            @ApiResponse(responseCode = "201", description = "Book borrowed successfully"),
            @ApiResponse(responseCode = "404", description = "Book or patron not found")
    })
    public Callable<ResponseEntity<Response<BorrowedBook>>> borrowBook(@Parameter(description = "Book ID") @PathVariable Long bookId, @Parameter(description = "Patron ID") @PathVariable Long patronId) {
        return () -> {
            Response<BorrowedBook> response = borrowingService.borrowBook(bookId, patronId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        };
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @Operation(summary = "Return a book", responses = {
            @ApiResponse(responseCode = "200", description = "Book returned successfully"),
            @ApiResponse(responseCode = "404", description = "Book or patron not found")
    })
    public Callable<ResponseEntity<Response<BorrowedBook>>> returnBook(@Parameter(description = "Book ID") @PathVariable Long bookId, @Parameter(description = "Patron ID") @PathVariable Long patronId) {
        return () -> {
            Response<BorrowedBook> response = borrowingService.returnBook(bookId, patronId);
            return ResponseEntity.ok(response);
        };
    }
}