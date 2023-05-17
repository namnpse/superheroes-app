package com.namnp.heroes.domain

import com.namnp.heroes.data.remote.HeroDto
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val currentPage: Int? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val data: List<HeroDto> = emptyList(),
    val lastUpdated: Long? = null
)

@Serializable
data class HeroResponse(
    val success: Boolean,
    val message: String? = null,
    val data: HeroDto? = null,
)
