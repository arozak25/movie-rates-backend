package com.arozaakk.movieratesbackend.repository

import com.arozaakk.movieratesbackend.entity.Movie
import com.arozaakk.movieratesbackend.entity.Rating
import com.arozaakk.movieratesbackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<Rating, Long> {
    fun existsByUserAndMovie(user: User, movie: Movie): Boolean
}