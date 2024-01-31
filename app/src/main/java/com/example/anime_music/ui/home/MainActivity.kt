package com.example.anime_music.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anime_music.R
import com.example.anime_music.data.models.Category
import com.example.anime_music.databinding.ActivityMainBinding
import com.example.anime_music.ui.home.adapters.CategoryAdapter
import com.example.anime_music.ui.home.adapters.NewSongsAdapter
import com.example.anime_music.ui.home.viewmodels.SongViewModel
import com.example.anime_music.ui.player.PlayerActivity

class MainActivity : AppCompatActivity() {

    //init binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewSongsAdapter
    private lateinit var adapterCategory: CategoryAdapter
    //init viewModels
    private val songViewModel: SongViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //retrofit = RetrofitClient().getRetrofitClient()
        initUI()
    }

    private fun loadSongs(){
        songViewModel.onCreated()
        songViewModel.newSongs.observe(this) { songs ->
            adapter.updateSongs(songs)
        }
    }

    private fun initUI() {
        initStatusBarColor()
        adapter = NewSongsAdapter(emptyList()){ position -> onItemSelected(position)}
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLastSongs.layoutManager = layoutManager
        binding.rvLastSongs.adapter = adapter
        initCategory()
        loadSongs()

    }

    private fun initStatusBarColor(){
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_app)

    }

    private fun initCategory(){
        adapterCategory = CategoryAdapter(getCategories())
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.layoutManager = layoutManager
        binding.rvCategories.adapter = adapterCategory
    }

    private fun getCategories(): List<Category>{
        return listOf(
            Category("One Piece"),
            Category("My Hero Academia"),
            Category("Digimon"),
            Category("Naruto")
        )
    }

    private fun onItemSelected(position: Int){
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(fileTag,adapter.getSong(position).file)
        intent.putExtra(songTag,adapter.getSong(position).title)
        intent.putExtra(imageTag,adapter.getSong(position).coverImage)
        intent.putExtra(authorTag,adapter.getSong(position).artist)
        intent.putExtra(idTag,position)
        startActivity(intent)
        
    }


    companion object{
        var fileTag = "file"
        var songTag = "song"
        var imageTag = "image"
        var authorTag = "author"
        var idTag = "id"
    }
}