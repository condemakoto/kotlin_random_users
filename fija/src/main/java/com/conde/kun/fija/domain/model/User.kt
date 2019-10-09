package com.conde.kun.fija.domain.model

class User(
    val userId: Long,
    val email: String,
    val confirmed: Boolean,
    val firstName: String,
    val lastName: String
)