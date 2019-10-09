package com.conde.kun.fija.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.conde.kun.fija.data.entity.FactEntity
import com.conde.kun.fija.data.entity.UserEntity
import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.model.FactUser

class FactMapper : BaseMapper<FactEntity, Fact>() {

    override fun map(input: FactEntity): Fact {
        return Fact(input.id, input.text, input.type, input.upvotes, input.user?.id, mapUser(input.user),false)
    }

    fun mapUser(input: UserEntity?): FactUser? {
        return input?.let { FactUser(input.id, input.name.first, input.name.last) }
    }

}