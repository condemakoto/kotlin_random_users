package com.conde.kun.randomusers.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val street: String?,
    val city: String?,
    val state: String?,
    val postCode: String?
): Parcelable

@Parcelize
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
): Parcelable