package com.conde.kun.randomusers.data.entity

data class Street(val number: Int?, val name: String?)

data class Location(val street: Street?, val city: String?, val state: String?, val postCode: String?)

data class Pictures(val large: String?, val medium: String?, val thumbnail: String?)

data class Name(val title: String?, val first: String?, val last: String?)

data class UserEntity(val gender: String?, val name: Name?, val location: Location?, val picture: Pictures?, val email: String?, val phone: String?)