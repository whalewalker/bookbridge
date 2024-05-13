package com.bookbridge.controller.v1;


import com.bookbridge.data.model.Patron;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.PatronService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
@Tag(name = "Patrons", description = "Operations related to patron management")
public class PatronController {

    private final PatronService patronService;

    @GetMapping
    @Operation(summary = "Get all patrons", responses = {
            @ApiResponse(responseCode = "200", description = "Patrons retrieved successfully")
    })
    public Callable<ResponseEntity<Response<Patron>>> getAllPatrons() {
        return () -> {
            Response<Patron> response = patronService.getAll();
            return ResponseEntity.ok(response);
        };
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a patron by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Patron retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Patron not found")
    })
    public Callable<ResponseEntity<Response<Patron>>> getPatronById(@Parameter(description = "Patron ID") @PathVariable Long id) {
        return () -> {
            Response<Patron> response = patronService.getById(id);
            return ResponseEntity.ok(response);
        };
    }

    @PostMapping
    @Operation(summary = "Create a new patron", responses = {
            @ApiResponse(responseCode = "200", description = "Patron created successfully")
    })
    public Callable<ResponseEntity<Response<Patron>>> createPatron(@Parameter(description = "Patron request") @Valid @RequestBody PatronRequest request) {
        return () -> {
            Response<Patron> response = patronService.create(request);
            return ResponseEntity.ok(response);
        };
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patron", responses = {
            @ApiResponse(responseCode = "200", description = "Patron updated successfully"),
            @ApiResponse(responseCode = "404", description = "Patron not found")
    })
    public Callable<ResponseEntity<Response<Patron>>> updatePatron(@Parameter(description = "Patron ID") @PathVariable Long id, @Parameter(description = "Patron request") @RequestBody PatronRequest request) {
        return () -> {
            Response<Patron> response = patronService.update(id, request);
            return ResponseEntity.ok(response);
        };
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patron", responses = {
            @ApiResponse(responseCode = "200", description = "Patron deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Patron not found")
    })
    public Callable<ResponseEntity<Response<?>>> deletePatron(@Parameter(description = "Patron ID") @PathVariable Long id) {
        return () -> {
            Response<?> response = patronService.delete(id);
            return ResponseEntity.ok(response);
        };
    }
}