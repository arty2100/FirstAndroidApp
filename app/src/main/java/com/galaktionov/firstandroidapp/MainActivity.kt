package com.galaktionov.firstandroidapp

import android.os.Bundle
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

const val url = "https://raw.githubusercontent.com/arty2100/gson/master/posts.json"

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = prepareList()
//        items.adapter = PostAdapter(list)
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
        val list = withContext(Dispatchers.IO) {

            client.get<MutableList<Post>>(url)

        }
        items.adapter = PostAdapter(list)

    }
}
