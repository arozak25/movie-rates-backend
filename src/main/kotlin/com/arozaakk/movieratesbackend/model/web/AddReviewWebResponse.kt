package com.arozaakk.movieratesbackend.model.web

import com.arozaakk.movieratesbackend.entity.Movie

data class AddReviewWebResponse(
    val id: Long,
    val reviewText: String,
    val movie: Movie
)

