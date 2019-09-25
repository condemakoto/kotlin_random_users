package com.conde.kun.randomusers.domain.model

data class Location(
    val street: String?,
    val city: String?,
    val state: String?,
    val postCode: String?
)

data class User(
    val username: String?,
    val title: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val thumbnailImageUrl: String?,
    val bigImageUrl: String?,
    val email: String?,
    val phone: String?,
    val location: Location?
)