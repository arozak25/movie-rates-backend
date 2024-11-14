package com.arozaakk.movieratesbackend.controller

import com.arozaakk.movieratesbackend.entity.User
import com.arozaakk.movieratesbackend.model.web.LoginWebRequest
import com.arozaakk.movieratesbackend.model.web.LoginWebResponse
import com.arozaakk.movieratesbackend.model.web.RegisterUserWebRequest
import com.arozaakk.movieratesbackend.model.web.WebResponse
import com.arozaakk.movieratesbackend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/auth"])
class UserController(val userService: UserService) {

    @PostMapping(
        value = ["/login"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@RequestBody request: LoginWebRequest): WebResponse<LoginWebResponse> {
        val response = userService.login(request)
        return WebResponse(HttpStatus.OK.value(), HttpStatus.OK.name, response)
    }

    @PostMapping(
        value = ["/register"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: RegisterUserWebRequest): WebResponse<User> {
        val response = userService.register(request)
        return WebResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name, response)
    }
}