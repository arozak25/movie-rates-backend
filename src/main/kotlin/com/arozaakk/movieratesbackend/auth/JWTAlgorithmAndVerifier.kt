package com.arozaakk.movieratesbackend.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

open class JWTAlgorithmAndVerifier {

    val algorithm: Algorithm = Algorithm.HMAC256("MovieRatesBacked")
    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer("MovieRatesBacked")
        .build()
}