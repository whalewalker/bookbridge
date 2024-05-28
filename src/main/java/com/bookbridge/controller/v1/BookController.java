package com.bookbridge.controller.v1;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Operations related to books management")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", responses = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    })
    public ResponseEntity<Response<Book>> getAllBooks() {
            Response<Book> response = bookService.getAll();
            return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully")
    })
    public ResponseEntity<Response<Book>> getBookById(@Parameter(description = "Book ID") @PathVariable Long id) {
            Response<Book> response = bookService.getById(id);
            return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Create a new book", responses = {
            @ApiResponse(responseCode = "200", description = "Book created successfully")
    })
    public ResponseEntity<Response<Book>> createBook(@Parameter(description = "Book request") @Valid @RequestBody BookRequest book) {
            Response<Book> response = bookService.create(book);
            return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", responses = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully")
    })
    public ResponseEntity<Response<Book>> updateBook(@Parameter(description = "Book ID") @PathVariable Long id, @Parameter(description = "Book request") @RequestBody BookRequest request) {
            Response<Book> response = bookService.update(id, request);
            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", responses = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully")
    })
    public ResponseEntity<Response<?>> deleteBook(@Parameter(description = "Book ID") @PathVariable Long id) {
            Response<?> response = bookService.delete(id);
            return ResponseEntity.ok(response);
    }
}