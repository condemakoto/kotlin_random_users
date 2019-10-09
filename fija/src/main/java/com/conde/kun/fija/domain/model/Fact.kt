package com.conde.kun.fija.domain.model

import androidx.room.*

@Entity(ignoredColumns = arrayOf("user"))
data class Fact(
    @PrimaryKey
    val id: String,
    val text: String,
    val type: String,
    var upvotes: Int,
    val userId: String?,
    var user: FactUser? = null,
    var favorited: Boolean = false
) {
    constructor(
        id: String,
        text: String,
        type: String,
        upvotes: Int,
        userId: String?,
        favorited: Boolean
    ) : this(id, text, type, upvotes, userId, null, favorited)
}

@Entity
data class FactUser(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val secondName: String
)

@Entity
data class FactsByUser(
    @Embedded
    val user: FactUser,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val facts: List<Fact>
)
