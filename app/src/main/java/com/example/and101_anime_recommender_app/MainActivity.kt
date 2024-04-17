package com.example.and101_anime_recommender_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.and101_anime_recommender_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding1: ActivityMainBinding

    private val btn get() = binding1.button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding1.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            }
    })}
}