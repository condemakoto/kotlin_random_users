package com.conde.kun.usersupport.data.datasource

interface UserSupportDataSource {
    suspend fun sendContactInfo(
        name: String,
        contactNumber: String,
        email: String,
        subject: String,
        message: String
    )
}