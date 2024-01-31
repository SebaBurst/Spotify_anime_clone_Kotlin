package com.example.anime_music.ui.home.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.anime_music.R
import com.example.anime_music.data.models.Category
import com.example.anime_music.databinding.ItemCategoryBinding

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemCategoryBinding.bind(view)
    fun bind(category: Category) {
        binding.tvCategoryName.text = category.name
        setImageCAtegory(category)


    }

    private fun setImageCAtegory(category: Category) {
        when(category.name){
            "One Piece" -> binding.ivCategoryIcon.setImageResource(R.drawable.luffy)
            "My Hero Academia" -> binding.ivCategoryIcon.setImageResource(R.drawable.deku)
            "Digimon" -> binding.ivCategoryIcon.setImageResource(R.drawable.taichi)
            "Naruto" -> binding.ivCategoryIcon.setImageResource(R.drawable.naruto)
        }
    }

}