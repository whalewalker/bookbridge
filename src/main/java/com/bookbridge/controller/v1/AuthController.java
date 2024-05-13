package com.bookbridge.controller.v1;

import com.bookbridge.data.request.LoginRequest;
import com.bookbridge.data.request.RegisterRequest;
import com.bookbridge.data.response.LoginResponse;
import com.bookbridge.data.request.ResetPasswordRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.AuthService;
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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Operations related to user authentication and registration")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", responses = {
            @ApiResponse(responseCode = "200", description = "User registered successfully")
    })
    public Callable<ResponseEntity<Response<?>>> createPatron(@Parameter(description = "User registration request") @Valid @RequestBody RegisterRequest request) {
        return () -> {
            Response<?> response = authService.create(request);
            return ResponseEntity.ok(response);
        };
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", responses = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully")
    })
    public Callable<ResponseEntity<Response<LoginResponse>>> createPatron(@Parameter(description = "User login request") @Valid @RequestBody LoginRequest request) {
        return () -> {
            Response<LoginResponse> response = authService.login(request);
            return ResponseEntity.ok(response);
        };
    }

    @PutMapping("/reset-password")
    @Operation(summary = "Reset user password", responses = {
            @ApiResponse(responseCode = "200", description = "Password reset successful")
    })
    public Callable<ResponseEntity<Response<?>>> resetPassword(@Parameter(description = "New password request") @RequestBody ResetPasswordRequest request) {
        return () -> {
            Response<?> response = authService.resetPassword( request);
            return ResponseEntity.ok(response);
        };
    }
}
