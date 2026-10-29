package com.example.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** What the client sends us. Validation rules live here. */
public record TodoRequest(
        @NotBlank(message = "title must not be blank")
        @Size(max = 100, message = "title must be at most 100 characters")
        String title,
        Boolean completed
) {}
