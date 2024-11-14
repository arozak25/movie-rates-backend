package com.arozaakk.movieratesbackend.model.web

import jakarta.validation.constraints.NotBlank

data class AddReviewWebRequest(
    @field:NotBlank
    val reviewText: String
)
