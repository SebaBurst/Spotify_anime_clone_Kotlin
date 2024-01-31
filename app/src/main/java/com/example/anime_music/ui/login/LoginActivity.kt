package com.example.anime_music.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anime_music.R
import com.example.anime_music.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //init binding
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }
    private fun initUI(){
    }
}