package com.galaktionov.firstandroidapp.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: Long,//milliseconds
    var likedByMe: Boolean = false,
    var likes: Int = 0,
    var comments: Int = 0,
    var shares: Int = 0,
    val address: String? = null,
    val location: Location? = null,
    val videoId: String? = null,
    val postTpe: POST_TYPE,
    val advLink: String? = null
) {
    enum class POST_TYPE {
        VIDEO, TEXT, REPLY, EVENT, ADV
    }
}



