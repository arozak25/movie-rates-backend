package com.arozaakk.movieratesbackend.model.web

import io.swagger.v3.oas.annotations.media.Schema


data class LoginWebRequest(
    @Schema(description = "Username", example = "ojakun")
    val username: String,

    @Schema(description = "Password", example = "Abc123!")
    val password: String
)
