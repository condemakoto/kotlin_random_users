package com.conde.kun.usersupport.domain.repository

interface ContactRepository {
    suspend fun sendContactInfo(
        name: String,
        contactNumber: String,
        email: String,
        subject: String,
        message: String
    )
}