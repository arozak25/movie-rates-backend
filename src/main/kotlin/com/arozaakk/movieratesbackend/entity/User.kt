package com.arozaakk.movieratesbackend.entity

import com.arozaakk.movieratesbackend.enum.RoleEnum
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*


@Table(name = "users")
@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: Long = 0,

    @Column(length = 255, nullable = false)
    val fullName: String,

    @Column(unique = true, length = 100, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val role: RoleEnum,

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    val createdAt: Date = Date(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Date? = null
)
