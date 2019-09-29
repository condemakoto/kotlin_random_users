package com.conde.kun.usersupport.data.request

data class ContactRequest(
    val name: String,
    val contactNumber: String,
    val email: String,
    val subject: String,
    val message: String
)