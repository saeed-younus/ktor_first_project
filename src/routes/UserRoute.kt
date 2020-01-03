package com.example.routes

import com.example.API_VERSION
import com.example.model.User
import com.example.request_model.CreateUserRequestModel
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

const val USER = "$API_VERSION/user"

fun Route.createUser(list: ArrayList<User>) {
    post(USER) {
        val request = call.receive<CreateUserRequestModel>()
        list.add(User(request.name, request.password))
        call.respond("Success")
    }
}

fun Route.getUsers(list: ArrayList<User>) {
    get(USER + "s") {
        call.respond(list.toArray())
    }
}