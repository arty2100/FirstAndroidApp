package com.galaktionov.firstandroidapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.galaktionov.firstandroidapp.adapters.PostAdapter
import com.galaktionov.firstandroidapp.dto.Post
import com.galaktionov.firstandroidapp.extensions.x
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = prepareList()
        items.adapter = PostAdapter(list)
        items.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun prepareList(): MutableList<Post> = mutableListOf(
        Post(
            1L,
            "Netology",
            "Funny cats!",
            1597796778000,
            true,
            2,
            0,
            1823,
            videoUrl = "https://www.youtube.com/embed/-bvXmLR3Ozc",
            postTpe = Post.POST_TYPE.VIDEO
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
            postTpe = Post.POST_TYPE.EVENT,
            companyImg = Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/1004px-Google_%22G%22_Logo.svg.png")
        ),
        Post(
            1L,
            "Twitter",
            "Twitter post",
            1587796778000,
            false,
            250,
            10,
            111,
            postTpe = Post.POST_TYPE.REPLY,
            companyImg = Uri.parse("https://upload.wikimedia.org/wikipedia/it/0/09/Twitter_bird_logo.png")
        ),
        Post(
            1L,
            "Google",
            "Try the best search engine!",
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
            videoUrl = "https://www.youtube.com/embed/-bvXmLR3Ozc",
            postTpe = Post.POST_TYPE.VIDEO,
            companyImg = Uri.parse("https://facebookbrand.com/wp-content/uploads/2019/04/f_logo_RGB-Hex-Blue_512.png?w=512&h=512")
        )
    )
}
