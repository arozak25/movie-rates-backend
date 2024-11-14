package com.arozaakk.movieratesbackend.service

import com.arozaakk.movieratesbackend.auth.JWTAlgorithmAndVerifier
import com.arozaakk.movieratesbackend.auth.UserSession
import com.arozaakk.movieratesbackend.enum.RoleEnum
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component
import java.util.*

const val ID_JWT_CLAIM = "id"
const val USERNAME_JWT_CLAIM = "username"
const val ROLE_CLAIM = "role"

@Component
class UserSessionService : JWTAlgorithmAndVerifier() {

    fun generateJWT(session: UserSession): String {
        return JWT.create()
            .withIssuer("MovieRatesBacked")
            .withClaim(ID_JWT_CLAIM, session.id)
            .withClaim(USERNAME_JWT_CLAIM, session.username)
            .withClaim(ROLE_CLAIM, session.role.name)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + 86400000))
            .withJWTId(UUID.randomUUID().toString())
            .sign(algorithm)
    }

    fun verifyJWT(jwt: String): UserSession {
        try {
            val decodedJWT: DecodedJWT = verifier.verify(jwt)
            return UserSession(
                decodedJWT.getClaim(USERNAME_JWT_CLAIM).asString(),
                decodedJWT.getClaim(ID_JWT_CLAIM).asLong(),
                RoleEnum.valueOf(decodedJWT.getClaim(ROLE_CLAIM).asString())
            )
        } catch (e: JWTVerificationException) {
            throw e
        }
    }
}