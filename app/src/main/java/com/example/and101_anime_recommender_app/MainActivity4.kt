package com.example.and101_anime_recommender_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.and101_anime_recommender_app.databinding.ActivityMain4Binding
import okhttp3.Headers
import org.json.JSONException

class ActivityMain4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeTitle = intent.getStringExtra("ANIME_TITLE") ?: ""
        fetchAnimeDetails(animeTitle)
    }

    private fun fetchAnimeDetails(title: String) {
        val client = AsyncHttpClient()
        val url = "https://api.jikan.moe/v4/anime?q=$title&limit=1"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                try {
                    val jsonObject = json.jsonObject.getJSONArray("data").getJSONObject(0)
                    binding.animeName.text = jsonObject.getString("title")
                    binding.animeRating.text = "Rating: ${jsonObject.getString("score")} / 10"
                    binding.animeStatus.text = "Status: ${jsonObject.getString("status")}"
                    binding.animeSynopsis.text = jsonObject.getString("synopsis")
                    Glide.with(this@ActivityMain4).load(jsonObject.getJSONObject("images").getJSONObject("jpg").getString("image_url")).into(binding.animeImage)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.e("Anime Details", "Failed: $errorResponse", throwable)
            }
        })
    }
}