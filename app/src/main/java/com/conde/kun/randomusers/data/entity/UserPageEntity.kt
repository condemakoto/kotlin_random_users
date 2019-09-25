package com.conde.kun.randomusers.data.entity

class Info(val page: Int?, val seed: String?, val results: Int?)

class UserPageEntity(val info: Info?, val results: Collection<UserEntity>)