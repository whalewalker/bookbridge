package com.bookbridge.controller.v1;


import com.bookbridge.data.model.Patron;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping
    public Callable<ResponseEntity<Response<Patron>>> getAllPatrons() {
        return () -> {
            Response<Patron> response = patronService.getAll();
            return ResponseEntity.ok(response);
        };
    }

    @GetMapping("/{id}")
    public Callable<ResponseEntity<Response<Patron>>> getPatronById(@PathVariable Long id) {
        return () -> {
            Response<Patron> response = patronService.getById(id);
            return ResponseEntity.ok(response);
        };
    }

    @PostMapping
    public Callable<ResponseEntity<Response<Patron>>> createPatron(@Valid @RequestBody PatronRequest request) {
        return () -> {
            Response<Patron> response = patronService.create(request);
            return ResponseEntity.ok(response);
        };
    }

    @PutMapping("/{id}")
    public Callable<ResponseEntity<Response<Patron>>> updatePatron(@PathVariable Long id, @RequestBody PatronRequest request) {
        return () -> {
            Response<Patron> response = patronService.update(id, request);
            return ResponseEntity.ok(response);
        };
    }

    @DeleteMapping("/{id}")
    public Callable<ResponseEntity<Response<?>>> deletePatron(@PathVariable Long id) {
        return () -> {
            Response<?> response = patronService.delete(id);
            return ResponseEntity.ok(response);
        };

    }
}