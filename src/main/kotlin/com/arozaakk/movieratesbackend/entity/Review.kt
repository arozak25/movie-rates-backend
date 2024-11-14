package com.arozaakk.movieratesbackend.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Table(name = "reviews")
@Entity
data class Review (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    var movie: Movie,

    @Column(length = 500, nullable = false)
    val reviewText: String,

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    val createdAt: Date = Date(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Date? = null

)