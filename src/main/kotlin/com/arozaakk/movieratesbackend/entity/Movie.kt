package com.arozaakk.movieratesbackend.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Table(name = "movies")
@Entity
data class Movie (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: Long = 0,

    @Column(length = 255, nullable = false)
    val title: String,

    @Column(length = 1500, nullable = false)
    val description: String,

    @Column(updatable = false, name = "release_date")
    val releaseDate: Date,

    @Column(length = 100, nullable = false)
    val director: String,

    @Column(length = 50, nullable = false)
    val genre: String,

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    val createdAt: Date = Date(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Date? = null,

    @Column(updatable = false, name = "created_by")
    val createdBy: Long,

    @UpdateTimestamp
    @Column(name = "updated_by")
    val updatedBy: Long? = null,

    //uni directional
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_tags",
        joinColumns = [JoinColumn(name = "movie_id", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "tag_id", nullable = false)]
    )
    val tags: Set<Tag> = setOf()
)

