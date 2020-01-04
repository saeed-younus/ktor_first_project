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

const val USER = "$API_VERSION/user"
fun Route.userApi(list: ArrayList<User>) {
    authenticate("jwt") {
        get(USER + "s") {
            call.respond(list.toArray())
        }
    }
    post(USER) {
        val request = call.receive<CreateUserRequestModel>()
        list.add(User(id++, request.name, request.password))
        call.respond("Success")
    }
}
