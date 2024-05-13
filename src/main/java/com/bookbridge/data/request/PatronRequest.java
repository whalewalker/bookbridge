package com.bookbridge.data.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Request object for creating a new patron")
@Data
public class PatronRequest {

    @Schema(description = "Name of the patron", example = "John Doe")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Email address of the patron", example = "john.doe@example.com")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Phone number of the patron", example = "1234567890")
    @NotBlank(message = "Password is required")
    private String phoneNumber;
}
