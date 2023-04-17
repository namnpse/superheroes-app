package com.namnp.heroes.data.local

import com.namnp.heroes.domain.model.Hero
import kotlinx.serialization.Serializable

@Serializable
data class HeroDto(
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val abilities: List<String>,
    val gender: String?,
    val month: String? = null,
    val day: String? = null,
    val family: List<String>? = null,
    val natureTypes: List<String>? = null,
    val realName: String? = null,
    val issues: Int? = null,
    val aliases: List<String>? = null,
    val collection: String?
)

fun HeroDto.toHero(): Hero {
    return Hero(
        id = id,
        name = name,
        image = image,
        about = about,
        rating = rating,
        power = power,
        abilities = abilities,
        month = month ?: "Unknown",
        day = day ?: "Unknown",
        family = family ?: emptyList(),
        natureTypes = natureTypes ?: emptyList(),
        gender = gender ?: "Male",
        realName = realName ?: "Unknown",
        issues = issues ?: 0,
        aliases = aliases ?: emptyList(),
        collection = collection ?: "",
    )
}
