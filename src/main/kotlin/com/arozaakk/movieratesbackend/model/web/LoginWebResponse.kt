package com.arozaakk.movieratesbackend.model.web

import com.arozaakk.movieratesbackend.enum.RoleEnum

data class LoginWebResponse(
    val token: String,
    val username: String,
    val fullName: String,
    val role: RoleEnum
)
