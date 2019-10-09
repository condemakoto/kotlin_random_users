package com.conde.kun.fija.data.entity

import com.google.gson.annotations.SerializedName

data class FactListEntity(val all: List<FactEntity>)

data class FactEntity(@SerializedName("_id") val id: String, val text: String, val type: String, val upvotes: Int, val user: UserEntity?)

data class UserEntity(@SerializedName("_id") val id: String, val name: Name)

data class Name(val first: String, val last: String)