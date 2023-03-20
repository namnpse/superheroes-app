package com.namnp.heroes.domain

import com.namnp.heroes.domain.model.Hero
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val currentPage: Int? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val data: List<Hero> = emptyList(),
    val lastUpdated: Long? = null
)
