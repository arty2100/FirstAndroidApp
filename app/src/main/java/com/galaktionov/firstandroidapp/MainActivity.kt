package com.galaktionov.firstandroidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galaktionov.firstandroidapp.dto.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val post = Post(1L, "Netology", "My first post!", "20-04-2020", true, 100, 34, 1823)

        mainText.text = post.content
        date.text = post.created
        company.text = post.author
        setValues(post)

        manageLikeButton(post)

    }

    private fun manageLikeButton(post: Post) {
        if (post.likedByMe) {
            likeIcon.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_24dp))
            likeText.setTextColor(resources.getColor(R.color.colorAccent))
        } else {
            likeIcon.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
            likeText.setTextColor(resources.getColor(android.R.color.black))
        }
    }

    private fun setValues(post: Post) {
        if (post.likes > 0) {
            if (post.likes > 999) {
                likeText.text = "999+"
            } else {
                likeText.text = post.likes.toString()
            }
        } else {
            likeText.text = ""
        }

        if (post.shares > 0) {
            if (post.shares > 999) {
                sharedText.text = "999+"
            } else {
                sharedText.text = post.shares.toString()
            }
        } else {
            sharedText.text = ""
        }

        if (post.comments > 0) {
            if (post.comments > 999) {
                сommentText.text = "999+"
            } else {
                сommentText.text = post.comments.toString()
            }
        } else {
            сommentText.text = ""
        }
    }
}
