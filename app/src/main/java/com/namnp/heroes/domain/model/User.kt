package com.namnp.heroes.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String? = "",
//    val uid: String? = null,
    val nickName: String? = "",
//    val username: String? = null,
//    val imageUrl: String? = "",
    val photoUrl: String? = null,
    val email: String? = "",
    val phone: String? = "",
    val bio: String? = "",
) {
//    constructor(nickName: String, phone: String, bio: String) : this("", nickName, "", "", phone, bio)
    constructor(photoUrl: String) : this("", "", photoUrl, "", "", "")
}
