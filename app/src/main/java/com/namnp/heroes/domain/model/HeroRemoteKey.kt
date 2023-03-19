package com.namnp.heroes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.namnp.heroes.util.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

// use for paging lib
@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
