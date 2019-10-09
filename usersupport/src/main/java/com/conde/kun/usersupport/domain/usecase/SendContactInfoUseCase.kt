package com.conde.kun.usersupport.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.usersupport.domain.repository.ContactRepository
import kotlinx.coroutines.CoroutineScope

class SendContactInfoUseCase(coroutineScope: CoroutineScope, val repository: ContactRepository) :
    BaseUseCase<Boolean, SendContactInfoUseCase.Params>(coroutineScope) {

    override suspend fun getData(param: Params): Boolean {
        repository.sendContactInfo(
            param.name,
            param.contactNumber,
            param.email,
            param.subject,
            param.message
        )
        return true
    }

    data class Params(
        val name: String,
        val contactNumber: String,
        val email: String,
        val subject: String,
        val message: String
    )
}