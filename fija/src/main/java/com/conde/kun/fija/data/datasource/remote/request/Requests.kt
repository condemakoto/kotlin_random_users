package com.conde.kun.fija.data.datasource.remote.request

data class LoginRequest(val username: String, val password: String)

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val userType: Int
)