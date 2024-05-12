package com.bookbridge.controller.v1;

import com.bookbridge.data.request.LoginRequest;
import com.bookbridge.data.request.RegisterRequest;
import com.bookbridge.data.response.LoginResponse;
import com.bookbridge.data.response.ResetPasswordRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public Callable<ResponseEntity<Response<?>>> createPatron(@Valid @RequestBody RegisterRequest request) {
        return () -> {
            Response<?> response = authService.create(request);
            return ResponseEntity.ok(response);
        };
    }

    @PostMapping("/login")
    public Callable<ResponseEntity<Response<LoginResponse>>> createPatron(@Valid @RequestBody LoginRequest request) {
        return () -> {
            Response<LoginResponse> response = authService.login(request);
            return ResponseEntity.ok(response);
        };
    }

    @PutMapping("/{userId}/reset-password")
    public Callable<ResponseEntity<Response<?>>> resetPassword(@PathVariable Long userId, @RequestBody ResetPasswordRequest request) {
        return () -> {
            Response<?> response = authService.resetPassword(userId, request.password());
            return ResponseEntity.ok(response);
        };
    }
}
