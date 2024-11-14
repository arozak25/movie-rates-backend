package com.arozaakk.movieratesbackend.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Table(name = "ratings")
@Entity
data class Rating (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: Long = 0,

    //uni directional
    @ManyToOne(fetch = FetchType.LAZY)
    var user: User,

    //uni directional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    var movie: Movie,

    @Column(updatable = false)
    val rating: Int,

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    val createdAt: Date = Date(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Date? = null

){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Rating) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
