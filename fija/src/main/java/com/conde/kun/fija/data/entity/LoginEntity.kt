package com.conde.kun.fija.data.entity

class LoginEntity(
    val user: UserInformationEntity?,
    val token: String?,
    errorCode: Long?,
    errorDescription: String?
) : BaseEntity(errorCode, errorDescription)

data class UserInformationEntity(
    val userId: Long,
    val mail: String,
    val confirmed: Boolean,
    val firstName: String,
    val lastName: String
)