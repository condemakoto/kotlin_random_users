package com.conde.kun.usersupport.data.datasource.remote


import com.conde.kun.usersupport.data.ServiceApi
import com.conde.kun.usersupport.data.ServiceApiConfigurator
import com.conde.kun.usersupport.data.datasource.UserSupportDataSource
import com.conde.kun.usersupport.data.request.ContactRequest

class RemoteUserSupoprtDataSource() : UserSupportDataSource {

    private val serviceApi: ServiceApi by lazy {
        ServiceApiConfigurator().serviceApi
    }

    override suspend fun sendContactInfo(
        name: String,
        contactNumber: String,
        email: String,
        subject: String,
        message: String
    ) {
        val request = ContactRequest(name, contactNumber, email, subject, message)
        serviceApi.sendContactInfo(request)
    }
}