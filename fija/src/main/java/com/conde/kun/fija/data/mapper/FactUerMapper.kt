package com.conde.kun.fija.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.conde.kun.fija.data.entity.FactEntity
import com.conde.kun.fija.domain.model.FactUser

class FactUserMapper : BaseMapper<FactEntity, FactUser?>() {

    override fun map(input: FactEntity): FactUser? {
        return input.user?.let {
            FactUser(it.id, it.name.first, it.name.last)
        }
    }

}