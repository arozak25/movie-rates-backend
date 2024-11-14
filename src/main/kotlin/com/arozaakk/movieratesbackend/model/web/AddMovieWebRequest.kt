package com.arozaakk.movieratesbackend.model.web

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.util.*

data class AddMovieWebRequest(
    @field:NotBlank
    @Schema(description = "Title", example = "Siksa Kubur")
    val title: String,

    @Schema(description = "Description", example = "Siksa Kubur hiii sereeeem")
    val description: String,

    val releaseDate: Date,

    @field:NotBlank
    @Schema(description = "Director", example = "Joko Anwar")
    val director: String,

    @field:NotBlank
    @Schema(description = "Genre", example = "Thriller")
    val genre: String,

    val tags: Set<String>
)
