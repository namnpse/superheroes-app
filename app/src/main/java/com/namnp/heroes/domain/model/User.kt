package com.namnp.heroes.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String?,
    val nickName: String?,
    val imageUrl: String? = null,
    val email: String?,
    val phone: String?,
    val bio: String?,
)
