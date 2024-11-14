package com.arozaakk.movieratesbackend.repository

import com.arozaakk.movieratesbackend.entity.Movie
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, Long> {
}