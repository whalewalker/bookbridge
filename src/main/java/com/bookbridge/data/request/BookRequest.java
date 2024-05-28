package com.bookbridge.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Request object for creating a new book")
@Data
public class BookRequest {

    @Schema(description = "Title of the book", example = "The Great Gatsby")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(description = "Author of the book", example = "F. Scott Fitzgerald")
    @NotBlank(message = "Author is required")
    private String author;

    @Schema(description = "Publication year of the book", example = "1925")
    @NotNull(message = "Publication year is required")
    private Integer publicationYear;

    @Schema(description = "ISBN number of the book", example = "978-0-7432-7356-5")
    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 digit")
    private String isbn;
}

