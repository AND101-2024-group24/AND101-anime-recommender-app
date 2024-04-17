package com.example.and101_anime_recommender_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter (private val animeNameList: List<String>): RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animeName: TextView

        init {
            // Find our RecyclerView item's TextView for future use
            animeName = view.findViewById(R.id.animeNameText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animeNameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animeNameString: String = animeNameList.get(position)
        // Set item views based on your views and data model
        val nameTextView = holder.animeName
        nameTextView.setText(animeNameString)


    }
}