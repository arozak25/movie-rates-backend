package com.arozaakk.movieratesbackend.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.List

@Configuration
class SpringDocConfiguration {

    @Bean
    fun customOpenAPI(): OpenAPI {
        val securitySchemeName = "JWT token"
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        securitySchemeName,
                        SecurityScheme()
                            .type(SecurityScheme.Type.APIKEY)
                            .`in`(SecurityScheme.In.HEADER)
                            .name("X-TOKEN-API")
                    )
            )
            .security(listOf(SecurityRequirement().addList(securitySchemeName)))
            .info(Info().title("Movie Rates Backend").version("1.0.0"))
    }
}