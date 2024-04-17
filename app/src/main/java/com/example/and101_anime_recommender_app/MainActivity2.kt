package com.example.and101_anime_recommender_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.and101_anime_recommender_app.databinding.ActivityMain2Binding
import com.example.and101_anime_recommender_app.databinding.ActivityMainBinding
import okhttp3.Headers

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding2: ActivityMain2Binding

    private lateinit var rvAnimes: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding2.root)

        rvAnimes = binding2.animeList

        retrieveAnimeList()
    }

    private fun retrieveAnimeList() {
        val client = AsyncHttpClient()
        client["https://api.jikan.moe/v4/schedules", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Stats", "response successful$json")
                val animeTitlesArray = json.jsonObject.getJSONArray("data")
                Log.d("array", animeTitlesArray.toString())
                val animeNamesList = mutableListOf<String>()

                for (i in 0 until animeTitlesArray.length()) {
                    animeNamesList.add(animeTitlesArray.getJSONObject(i).getString("title"))
                }
                val adapter = AnimeAdapter(animeNamesList)
                rvAnimes.adapter = adapter
                rvAnimes.layoutManager = LinearLayoutManager(this@MainActivity2)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Stats", errorResponse)
            }
        }]
    }
}