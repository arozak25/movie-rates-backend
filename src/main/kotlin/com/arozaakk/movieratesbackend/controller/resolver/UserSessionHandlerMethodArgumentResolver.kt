package com.arozaakk.movieratesbackend.controller.resolver

import com.arozaakk.movieratesbackend.auth.UserSession
import com.arozaakk.movieratesbackend.exception.UnauthorizedException
import com.arozaakk.movieratesbackend.service.UserSessionService
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserSessionHandlerMethodArgumentResolver(private val userSessionService: UserSessionService) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == UserSession::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val jwt = webRequest.getHeader("X-TOKEN-API") ?: throw UnauthorizedException("Unauthrorized")
        return userSessionService.verifyJWT(jwt)
    }
}