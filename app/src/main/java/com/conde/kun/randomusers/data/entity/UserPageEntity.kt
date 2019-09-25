package com.conde.kun.randomusers.data.entity

data class Info(val page: Int?, val seed: String?, val results: Int?)

data class UserPageEntity(val info: Info?, val results: Collection<UserEntity>?)