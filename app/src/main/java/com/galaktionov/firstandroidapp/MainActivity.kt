package com.galaktionov.firstandroidapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galaktionov.firstandroidapp.adapters.PostAdapter
import com.galaktionov.firstandroidapp.dto.Post
import com.galaktionov.firstandroidapp.extensions.x
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = mutableListOf(
            Post(
                1L,
                "Netology",
                "Funny cats!",
                1597796778000,
                true,
                2,
                0,
                1823,
                videoId = "-bvXmLR3Ozc", postTpe = Post.POST_TYPE.VIDEO
            ),
            Post(
                1L,
                "Google",
                "Фестиваль книги",
                1587711778000,
                false,
                20,
                100,
                300,
                "Nevsky Prospect",
                59.932030 x 30.355610,
                postTpe = Post.POST_TYPE.EVENT
            ),
            Post(
                1L,
                "Microsoft",
                "Microsoft post",
                1587796778000,
                false,
                250,
                10,
                111, postTpe = Post.POST_TYPE.REPLY
            ),
            Post(
                1L,
                "Goolge",
                "Use the best search engine!",
                1523496778000,
                true,
                5,
                1,
                0,
                postTpe = Post.POST_TYPE.ADV,
                advLink = Uri.parse("http://www.google.com"),
                companyImg = Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/1004px-Google_%22G%22_Logo.svg.png")
            ),
            Post(
                1L,
                "Facebook",
                "Facebook post",
                1587567778000,
                false,
                16,
                12,
                3,
                null,
                null,
                videoId = "-bvXmLR3Ozc", postTpe = Post.POST_TYPE.VIDEO
            )
        )
        items.adapter = PostAdapter(list)
    }
}
