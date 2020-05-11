package com.galaktionov.firstandroidapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.galaktionov.firstandroidapp.adapters.PostAdapter
import com.galaktionov.firstandroidapp.dto.Post
import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.http.ContentType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

const val posts_url = "https://raw.githubusercontent.com/arty2100/gson/master/posts.json"
const val adv_posts_url = "https://raw.githubusercontent.com/arty2100/gson/master/adv_posts.json"

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareList()
        items.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun prepareList() = launch {

        val client = HttpClient {
            install(JsonFeature) {
                acceptContentTypes = listOf(
                    ContentType.Text.Plain,
                    ContentType.Application.Json
                )
                serializer = GsonSerializer()
            }
        }
        val posts = withContext(Dispatchers.IO) {
            client.get<MutableList<Post>>(posts_url)
        }
        val advPosts = withContext(Dispatchers.IO) {
            client.get<MutableList<Post>>(adv_posts_url)
        }
        progress.visibility = View.GONE
        val adapter = PostAdapter()
        items.adapter = adapter
        adapter.setItems(posts, advPosts)

    }
}
