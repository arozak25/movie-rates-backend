package com.arozaakk.movieratesbackend.repository

import com.arozaakk.movieratesbackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByUsername(username: String): Boolean

    fun findFirstByUsername(username: String): User?
}