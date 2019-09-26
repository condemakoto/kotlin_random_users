package com.conde.kun.randomusers.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.conde.kun.randomusers.data.entity.UserEntity
import com.conde.kun.randomusers.domain.model.User

class UserMapper : BaseMapper<UserEntity, User>() {

    val locationMapper = LocationMapper()

    override fun map(input: UserEntity): User {
        return User(
            input.email,
            input.name?.title,
            input.name?.first,
            input.name?.last,
            input.gender,
            input.picture?.thumbnail,
            input.picture?.large,
            input.email,
            input.phone,
            locationMapper.map(input.location)
        )
    }

}