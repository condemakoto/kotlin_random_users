package com.conde.kun.randomusers.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.conde.kun.randomusers.data.entity.Location

class LocationMapper : BaseMapper<Location?, com.conde.kun.randomusers.domain.model.Location>() {

    override fun map(input: Location?): com.conde.kun.randomusers.domain.model.Location {
        return com.conde.kun.randomusers.domain.model.Location(
            input?.street,
            input?.city,
            input?.state,
            input?.postCode
        )
    }
}