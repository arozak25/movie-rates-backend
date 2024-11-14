package com.arozaakk.movieratesbackend.repository

import com.arozaakk.movieratesbackend.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : JpaRepository<Tag, Long> {
    fun existsByName(name: String): Boolean
    fun findFirstByName(name: String): Tag?
}