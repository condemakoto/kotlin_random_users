package com.conde.kun.randomusers.data.entity

class Location(val street: String?, val city: String?, val state: String?, val postCode: String?)

class Pictures(val large: String?, val medium: String?, val thumbnail: String?)

class Name(val title: String?, val first: String?, val last: String?)

class UserEntity(val gender: String?, val name: Name?, val location: Location?, val picture: Pictures?, val email: String?, val phone: String?)