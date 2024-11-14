package com.arozaakk.movieratesbackend.service

import com.arozaakk.movieratesbackend.auth.UserSession
import com.arozaakk.movieratesbackend.entity.User
import com.arozaakk.movieratesbackend.enum.RoleEnum
import com.arozaakk.movieratesbackend.model.web.LoginWebRequest
import com.arozaakk.movieratesbackend.model.web.LoginWebResponse
import com.arozaakk.movieratesbackend.model.web.RegisterUserWebRequest
import com.arozaakk.movieratesbackend.repository.UserRepository
import jakarta.validation.Valid
import jakarta.validation.ValidationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Validated
interface UserService {

    fun register(@Valid request: RegisterUserWebRequest): User

    fun login(@Valid request: LoginWebRequest): LoginWebResponse
}

@Service
class UserServiceImpl(val userRepository: UserRepository, val userSessionService: UserSessionService): UserService {

    override fun register(request: RegisterUserWebRequest): User {
        if (userRepository.existsByUsername(request.username.lowercase()))
            throw ValidationException("Username already taken")

        val password = BCryptPasswordEncoder().encode(request.password)
        val user = User(
            fullName = request.fullName,
            username = request.username,
            password = password,
            role = RoleEnum.USER
        )

        return userRepository.save(user)
    }

    override fun login(request: LoginWebRequest): LoginWebResponse {
        val user = userRepository.findFirstByUsername(request.username)
            ?: throw ValidationException("User not found")

        if (!BCryptPasswordEncoder().matches(request.password, user.password))
            throw ValidationException("Invalid password")

        val token = userSessionService.generateJWT(UserSession(user.username, user.id, user.role))
        return LoginWebResponse(token, user.username, user.fullName, user.role)
    }

}