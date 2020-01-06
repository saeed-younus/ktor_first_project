package com.example.model

import io.ktor.http.HttpStatusCode

sealed class Response<T>(
    open val data: T?,
    open val status: Int,
    open val message: String
)

data class Success<T>(
    override val data: T,
    override val status: Int,
    override val message: String
) : Response<T>(data, status, message)

data class Error<T>(
    override val message: String
) : Response<T>(null, HttpStatusCode.InternalServerError.value, message)