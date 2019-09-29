package com.conde.kun.usersupport.data.repository

import com.conde.kun.usersupport.data.datasource.UserSupportDataSource
import com.conde.kun.usersupport.domain.repository.ContactRepository

class ContactRepositoryImplementation(val dataSource: UserSupportDataSource) : ContactRepository {

    override suspend fun sendContactInfo(
        name: String,
        contactNumber: String,
        email: String,
        subject: String,
        message: String
    ) {
        dataSource.sendContactInfo(name, contactNumber, email, subject, message)
    }
}