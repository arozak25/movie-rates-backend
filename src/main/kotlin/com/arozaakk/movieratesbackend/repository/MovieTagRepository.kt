package com.arozaakk.movieratesbackend.repository

import com.arozaakk.movieratesbackend.entity.MovieTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieTagRepository : JpaRepository<MovieTag, Long> {
}