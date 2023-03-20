package com.namnp.heroes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.namnp.heroes.util.Constants.HERO_REMOTE_KEYS_DATABASE_TABLE

// use for paging lib
@Entity(tableName = HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
