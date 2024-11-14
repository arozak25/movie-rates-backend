package com.arozaakk.movieratesbackend.configuration

import com.arozaakk.movieratesbackend.controller.resolver.UserSessionHandlerMethodArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class MvcConfiguration(
    val userSessionHandlerMethodArgumentResolver: UserSessionHandlerMethodArgumentResolver
) : WebMvcConfigurer {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(userSessionHandlerMethodArgumentResolver)
    }
}