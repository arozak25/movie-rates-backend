package com.arozaakk.movieratesbackend.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.util.*


@Table(name = "movie_tags")
@IdClass(MovieTag.MovieTagPK::class)
@Entity
data class MovieTag(

    @Id
    @Column(name = "movie_id", nullable = false)
    val movieId: Long,

    @Id
    @Column(name = "tag_id", nullable = false)
    val tagId: Long,

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    val createdAt: Date = Date(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Date? = null,

    @Column(updatable = false, name = "created_by")
    val createdBy: Long,

    @Column(name = "updated_by")
    val updatedBy: Long? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as MovieTagPK

        if (movieId != other.movieId) return false
        if (tagId != other.tagId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movieId
        result = 31 * result + tagId
        return result.toInt()
    }

    @Embeddable
    data class MovieTagPK(
        @Column(name = "movie_id", nullable = false)
        var movieId: Long? = null,

        @Column(name = "tag_id", nullable = false)
        var tagId: Long? = null
    )
}