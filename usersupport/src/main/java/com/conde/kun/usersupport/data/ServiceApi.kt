package com.conde.kun.usersupport.data

import com.conde.kun.usersupport.data.request.ContactRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    @POST("/api/contact")
    suspend fun sendContactInfo(@Body contactInfo: ContactRequest)
}