package com.arozaakk.movieratesbackend.repository

import com.arozaakk.movieratesbackend.entity.Movie
import com.arozaakk.movieratesbackend.entity.Review
import com.arozaakk.movieratesbackend.entity.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<Review, Long> {
    fun existsByUserAndMovie(user: User, movie: Movie): Boolean

    @EntityGraph(attributePaths = ["movie", "user"])
    fun findAllByMovie(movie: Movie): List<Review>
}