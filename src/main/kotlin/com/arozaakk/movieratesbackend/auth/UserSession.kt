package com.arozaakk.movieratesbackend.auth

import com.arozaakk.movieratesbackend.enum.RoleEnum

data class UserSession(
    val username: String,
    val id: Long,
    val role: RoleEnum
)
