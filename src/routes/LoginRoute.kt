package com.example.routes

import com.example.API_VERSION
import com.example.JwtService
import com.example.model.Success
import com.example.model.User
import com.example.request_model.CreateUserRequestModel
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post


const val LOGIN = "$API_VERSION/login"

var id = 0

fun Route.loginApi(list: ArrayList<User>, jwtService: JwtService) {
    post(USER) {
        val request = call.receive<CreateUserRequestModel>()
        val user = User(id++, request.name, request.password)
        list.add(user)
        val token = jwtService.generateToken(user)

        call.respond(Success<String>(data = token, status = HttpStatusCode.OK.value, message = "Successfully login"))
    }
}
