package com.example.anime_music.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anime_music.R
import com.example.anime_music.data.models.Category
import com.example.anime_music.ui.home.viewholders.CategoryViewHolder

class CategoryAdapter(var categories: List<Category>): RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(layoutInflater.inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}
