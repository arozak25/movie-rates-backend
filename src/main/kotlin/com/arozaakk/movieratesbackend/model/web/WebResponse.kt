package com.arozaakk.movieratesbackend.model.web

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)

data class WebResponsePaginated<T>(
    val code: Int,
    val status: String,
    val pagination: Pagination,
    val data: T
)

data class ErrorResponse(
    val code: Int,
    val status: String,
    val message: String?
)

data class Pagination(
    val totalData: Int,
    val totalPage: Int,
    val page: Int,
)
