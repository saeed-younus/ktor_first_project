package com.example.routes

import com.example.API_VERSION
import com.example.model.User
import com.example.request_model.CreateUserRequestModel
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post


const val LOGIN = "$API_VERSION/login"

var id = 0

fun Route.loginApi(list: ArrayList<User>) {
    post(USER) {
        val request = call.receive<CreateUserRequestModel>()
        list.add(User(id++, request.name, request.password))
        call.respond("Success")
    }
}
