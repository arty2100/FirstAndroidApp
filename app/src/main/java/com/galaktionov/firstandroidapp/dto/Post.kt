package com.galaktionov.firstandroidapp.dto

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: String, // пока строка
    var likedByMe: Boolean = false,
    var likes: Int = 0,
    var comments: Int = 0,
    var shares: Int = 0

)