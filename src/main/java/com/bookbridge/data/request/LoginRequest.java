package com.bookbridge.data.request;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for user login")
public record LoginRequest(
        @Schema(description = "Email address of the user", example = "user@example.com")
        String email,
        @Schema(description = "Password of the user", example = "password123")
        String password) {
}
