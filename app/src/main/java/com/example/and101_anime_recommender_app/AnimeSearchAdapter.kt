package com.example.and101_anime_recommender_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.and101_anime_recommender_app.databinding.AnimeItemBinding

class AnimeSearchAdapter(
    private var animeTitles: List<String>,
    private var animeImageUrls: List<String>
) : RecyclerView.Adapter<AnimeSearchAdapter.ViewHolder>() {

    class ViewHolder(private val binding: AnimeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String, imageUrl: String) {
            binding.animeName.text = title
            Glide.with(binding.animeImage.context).load(imageUrl).into(binding.animeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(animeTitles[position], animeImageUrls[position])
    }

    override fun getItemCount(): Int = animeTitles.size

    fun updateData(newAnimeTitles: List<String>, newAnimeImageUrls: List<String>) {
        animeTitles = newAnimeTitles
        animeImageUrls = newAnimeImageUrls
    }
}