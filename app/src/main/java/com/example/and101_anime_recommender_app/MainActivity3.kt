package com.example.and101_anime_recommender_app
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.and101_anime_recommender_app.databinding.ActivityMain3Binding
import okhttp3.Headers
import org.json.JSONException

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val query = binding.searchInput.text.toString().trim()
            if (query.isNotEmpty()) {
                fetchAnimeData(query)
            }
        }

        setupRecyclerView()
    }

    private fun fetchAnimeData(query: String) {
        val client = AsyncHttpClient()
        val url = "https://api.jikan.moe/v4/anime?q=$query&limit=20"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                try {
                    val jsonArray = json.jsonObject.getJSONArray("data")
                    val animeTitles = mutableListOf<String>()
                    val animeImageUrls = mutableListOf<String>()

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        animeTitles.add(jsonObject.getString("title"))
                        animeImageUrls.add(jsonObject.getJSONObject("images").getJSONObject("jpg").getString("image_url"))
                    }
                    (binding.animeList.adapter as AnimeSearchAdapter).updateData(animeTitles, animeImageUrls)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.e("Anime API", "Failed: $errorResponse", throwable)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.animeList.layoutManager = LinearLayoutManager(this)
        binding.animeList.adapter = AnimeSearchAdapter(emptyList(), emptyList())
    }
}