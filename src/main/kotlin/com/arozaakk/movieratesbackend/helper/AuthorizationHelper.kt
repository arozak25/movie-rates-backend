package com.arozaakk.movieratesbackend.helper

import com.arozaakk.movieratesbackend.auth.UserSession
import com.arozaakk.movieratesbackend.enum.RoleEnum
import com.arozaakk.movieratesbackend.exception.UnauthorizedException

class AuthorizationHelper {
    companion object {
        fun validateRole(userSession: UserSession, expectedRole: RoleEnum): Boolean {
            if (userSession.role != expectedRole)
                throw UnauthorizedException("Unauthorized")

            return true
        }
    }
}