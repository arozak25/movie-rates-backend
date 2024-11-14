package com.arozaakk.movieratesbackend.model.dto

data class MovieWithAvgRatingDTO(
    val movieId: Long,
    val title: String,
    val averageRating: Double
)
