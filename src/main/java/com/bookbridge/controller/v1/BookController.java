package com.bookbridge.controller.v1;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public Callable<ResponseEntity<Response<Book>>> getAllBooks() {
        return () -> {
            Response<Book> response = bookService.getAll();
            return ResponseEntity.ok(response);
        };
    }

    @GetMapping("/{id}")
    public Callable<ResponseEntity<Response<Book>>> getBookById(@PathVariable Long id) {
        return () -> {
            Response<Book> response = bookService.getById(id);
            return ResponseEntity.ok(response);
        };
    }

    @PostMapping
    public Callable<ResponseEntity<Response<Book>>> createBook(@Valid @RequestBody BookRequest book) {
        return () -> {
            Response<Book> response = bookService.create(book);
            return ResponseEntity.ok(response);
        };
    }

    @PutMapping("/{id}")
    public Callable<ResponseEntity<Response<Book>>> updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return () -> {
            Response<Book> response = bookService.update(id, request);
            return ResponseEntity.ok(response);
        };
    }

    @DeleteMapping("/{id}")
    public Callable<ResponseEntity<Response<?>>> deleteBook(@PathVariable Long id) {
        return () -> {
            Response<?> response = bookService.delete(id);
            return ResponseEntity.ok(response);
        };
    }

}