package com.arozaakk.movieratesbackend.model.web

import com.arozaakk.movieratesbackend.entity.Movie

data class AddRatingWebResponse(
    val id: Long,
    val rating: Int,
    val movie: Movie
)
