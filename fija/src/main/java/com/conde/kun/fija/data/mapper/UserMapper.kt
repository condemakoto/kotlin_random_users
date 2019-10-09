package com.conde.kun.fija.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.conde.kun.fija.data.entity.UserInformationEntity
import com.conde.kun.fija.domain.model.User

class UserMapper : BaseMapper<UserInformationEntity, User>() {

    override fun map(user: UserInformationEntity): User {
        return User(user.userId, user.mail, user.confirmed, user.firstName, user.lastName)
    }

}