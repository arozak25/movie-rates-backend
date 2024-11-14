package com.arozaakk.movieratesbackend.model.web

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class RegisterUserWebRequest(
    @field:NotBlank
    @Schema(description = "Full name", example = "Rozak")
    val fullName: String,

    @field:NotBlank
    @Schema(description = "Username", example = "ojakun")
    val username: String,

    @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
        message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    @Schema(description = "Password", example = "Abc123!")
    val password: String
)
