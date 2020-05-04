package com.galaktionov.firstandroidapp

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
                1587796778000,
                true,
                2,
                0,
                1823,
                "Nevsky Prospect",
                59.932030 x 30.355610,
                "-bvXmLR3Ozc"
            ),
            Post(
                1L,
                "Google",
                "Funny cats!",
                1587796778000,
                false,
                20,
                100,
                300,
                "Nevsky Prospect",
                59.932030 x 30.355610,
                "-bvXmLR3Ozc"
            ),
            Post(
                1L,
                "Microsoft",
                "Funny cats!",
                1587796778000,
                true,
                25,
                189,
                1,
                "Nevsky Prospect",
                59.932030 x 30.355610,
                "-bvXmLR3Ozc"
            )
        )

        items.adapter = PostAdapter(list)

    }
}
