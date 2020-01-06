package com.example.routes

import com.example.API_VERSION
import com.example.JwtService
import com.example.model.Success
import com.example.model.User
import com.example.request_model.CreateUserRequestModel
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

const val USER = "$API_VERSION/user"
fun Route.userApi(list: ArrayList<User>, jwtService: JwtService) {
    authenticate("jwt") {
        get(USER + "s") {
            call.respond(list.toArray())
        }
    }
    post(USER) {
        val request = call.receive<CreateUserRequestModel>()
        val user = User(id++, request.name, request.password)
        list.add(user)
        val token = jwtService.generateToken(user)
        call.respond(Success(data = token, status = HttpStatusCode.OK.value, message = "Successfully login"))
    }
}
