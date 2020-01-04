package com.example.model

import io.ktor.auth.Principal


data class User(val id: Int? = null, val displayName: String, val password: String) : Principal