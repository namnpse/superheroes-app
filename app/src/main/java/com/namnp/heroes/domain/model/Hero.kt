package com.namnp.heroes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.namnp.heroes.util.Constants.HERO_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = HERO_DATABASE_TABLE)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val about: String = "",
    val rating: Double = 0.0,
    val power: Int = 0,
    val abilities: List<String> = emptyList(),
    val gender: String = "Male",
    val month: String = "",
    val day: String = "",
    val family: List<String> = emptyList(),
    val natureTypes: List<String> = emptyList(),
    val realName: String? = null,
    val issues: Int? = null,
    val aliases: List<String>? = null,
    val collection: String = "",
)
