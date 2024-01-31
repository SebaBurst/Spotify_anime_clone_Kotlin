package com.example.anime_music.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anime_music.R
import com.example.anime_music.data.models.SongsResultItem
import com.example.anime_music.ui.home.viewholders.NewSongsViewHolder

class NewSongsAdapter(private var songs: List<SongsResultItem>, private val onItemSelect: (Int) -> Unit): RecyclerView.Adapter<NewSongsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewSongsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewSongsViewHolder(layoutInflater.inflate(R.layout.item_newsong, parent, false))
    }
    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: NewSongsViewHolder, position: Int) {
        holder.bind(songs[position], onItemSelect)
        holder.itemView.setOnClickListener {
            onItemSelect(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateSongs(songs: List<SongsResultItem>){
        this.songs = songs
        notifyDataSetChanged()
    }

    fun getSong(position: Int): SongsResultItem{
        return songs[position]
    }
}