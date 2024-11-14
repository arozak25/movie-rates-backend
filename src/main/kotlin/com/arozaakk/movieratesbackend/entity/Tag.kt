package com.arozaakk.movieratesbackend.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Table(name = "tags")
@Entity
data class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: Long = 0,

    @Column(length = 255, nullable = false)
    val name: String,

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


)