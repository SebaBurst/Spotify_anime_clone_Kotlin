package com.example.anime_music.ui.home.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.anime_music.data.models.SongsResultItem
import com.example.anime_music.databinding.ItemNewsongBinding
import com.squareup.picasso.Picasso

class NewSongsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemNewsongBinding.bind(view)

    fun bind(song: SongsResultItem, onItemSelect: (Int) -> Unit) {
        binding.tvSongNameIcon.text = song.title
        binding.tvSongAuthorIcon.text = song.artist
        Picasso.get()
            .load(song.coverImage)
            .into(binding.ivSongCoverIcon)
    }

}