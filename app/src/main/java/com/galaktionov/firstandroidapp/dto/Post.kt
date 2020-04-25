package com.galaktionov.firstandroidapp.dto

import java.security.Timestamp

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: Long ,//milliseconds
    var likedByMe: Boolean = false,
    var likes: Int = 0,
    var comments: Int = 0,
    var shares: Int = 0

)