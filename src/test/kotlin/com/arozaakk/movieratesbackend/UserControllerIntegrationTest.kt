package com.arozaakk.movieratesbackend

import com.arozaakk.movieratesbackend.entity.User
import com.arozaakk.movieratesbackend.model.web.LoginWebRequest
import com.arozaakk.movieratesbackend.model.web.LoginWebResponse
import com.arozaakk.movieratesbackend.model.web.RegisterUserWebRequest
import com.arozaakk.movieratesbackend.model.web.WebResponse
import com.arozaakk.movieratesbackend.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        // Set up necessary data or mocks if needed
    }

    @Test
    fun `test register user successfully`() {
        val number = (0..1000).random()
        val registerRequest = RegisterUserWebRequest("Abdul $number", "username$number", "Kiskis123!")

        mockMvc.perform(
            post("/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(HttpStatus.CREATED.value()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.CREATED.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("username$number"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("Abdul $number"))
    }

    @Test
    fun `test login successfully`() {
        val loginRequest = LoginWebRequest("ojakun", "Abc123!")

        mockMvc.perform(
            post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(HttpStatus.OK.value()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.OK.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists()) // Ensure response data is returned
    }
}