package com.conde.kun.randomusers.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.conde.kun.randomusers.data.entity.Location
import com.conde.kun.randomusers.data.entity.Street

class LocationMapper : BaseMapper<Location?, com.conde.kun.randomusers.domain.model.Location>() {

    override fun map(input: Location?): com.conde.kun.randomusers.domain.model.Location {
        return com.conde.kun.randomusers.domain.model.Location(
            mapStreet(input?.street),
            input?.city,
            input?.state,
            input?.postCode
        )
    }

    fun mapStreet(street: Street?): String {
        val sb = StringBuilder()
        sb.append(street?.name).append(" ").append(street?.number)
        return sb.toString()
    }
}