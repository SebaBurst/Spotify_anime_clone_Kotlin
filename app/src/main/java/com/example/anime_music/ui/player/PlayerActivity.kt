package com.example.anime_music.ui.player


import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.example.anime_music.R
import com.example.anime_music.data.models.SongsResult
import com.example.anime_music.data.models.SongsResultItem
import com.example.anime_music.data.network.ApiService
import com.example.anime_music.data.network.RetrofitClient
import com.example.anime_music.databinding.ActivityPlayerBinding
import com.example.anime_music.ui.home.MainActivity.Companion.authorTag
import com.example.anime_music.ui.home.MainActivity.Companion.fileTag
import com.example.anime_music.ui.home.MainActivity.Companion.idTag
import com.example.anime_music.ui.home.MainActivity.Companion.imageTag
import com.example.anime_music.ui.home.MainActivity.Companion.songTag
import com.example.anime_music.util.Util.convertTime
import com.example.anime_music.util.Util.getColorFromImage
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var imageCover: String
    private lateinit var songName: String
    private lateinit var songAuthor: String
    private lateinit var songFile: String
    private var idSong: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSongDetails()
        initUI()
        initListeners()
    }

    private fun initUI(){
        initSongDetails()
        initBackgroundColor()
        initMusicPlayer()
    }

    private fun initSongDetails(){
        binding.tvSongName.text = songName
        binding.tvAuthor.text = songAuthor
        Glide.with(this)
            .load(imageCover)
            .into(binding.ivCover)
        Picasso.get().load(imageCover).into(binding.ivCover)
    }

    private fun getSongDetails(){
        imageCover = intent.extras?.getString(imageTag) ?: ""
        songName = intent.extras?.getString(songTag) ?: ""
        songAuthor = intent.extras?.getString(authorTag) ?: ""
        songFile = intent.extras?.getString(fileTag) ?: ""
        idSong = intent.extras?.getInt(idTag) ?: 0


    }

    private fun initListeners(){
        binding.btnNext.setOnClickListener {
            nextSong()
        }
    }


    // Cambia el color de la barra de estado y el fondo de la actividad
    private fun initBackgroundColor(){
        binding.root.background = getColorFromImage(binding.ivCover.drawable!!,this)

    }
    // Dentro de la función initMusicPlayer
    private fun initMusicPlayer() {
        CoroutineScope(Dispatchers.IO).launch {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(songFile)
            mediaPlayer.prepare()
            withContext(Dispatchers.Main) {
                // Resto del código de inicialización en el hilo principal
                binding.sliderTrack.max = mediaPlayer.duration
                binding.tvTotalTime.text = convertTime(mediaPlayer.duration)
                // Se inicializa el boton de play
                binding.btnPlay.setOnClickListener {
                    if(!mediaPlayer.isPlaying){
                        mediaPlayer.start()
                        binding.btnPlay.setImageResource(R.drawable.ic_pause)
                    }
                    else{
                        mediaPlayer.pause()
                        binding.btnPlay.setImageResource(R.drawable.ic_start)

                    }
                }
                // se inicializa el evento del slider
                binding.sliderTrack.setOnSeekBarChangeListener(object: android.widget.SeekBar.OnSeekBarChangeListener{
                    // Se actualiza el slider cuando se reproduce la musica
                    override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
                        if(fromUser){
                            mediaPlayer.seekTo(progress)
                        }
                    }
                    // Se pausa la musica cuando se mueve el slider
                    override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {
                        mediaPlayer.pause()
                    }

                    // Se reanuda la musica cuando se deja de mover el slider
                    override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {
                        mediaPlayer.start()
                    }


                })
                // ... (resto del código)
            }
            // Se actualiza el slider cada segundo con coroutines
            while (true) {
                withContext(Dispatchers.Main) {
                    binding.sliderTrack.progress = mediaPlayer.currentPosition
                    binding.tvCurrentTime.text = convertTime(mediaPlayer.currentPosition)
                }
                kotlinx.coroutines.delay(1000)
            }
        }
    }
    //init music player
    private fun nextSong(){
        val retrofit = RetrofitClient().getRetrofitClient()
        CoroutineScope(Dispatchers.IO).launch {
            val responseResult: SongsResult = retrofit.create(ApiService::class.java).getSongs();
            if(responseResult.isNotEmpty()){
               val sizeList = responseResult.size-1
                var random: Int

                do {
                    random = Random.nextInt(sizeList)
                } while (random == idSong )


                // Se obtiene la cancion aleatoria
                val song = responseResult[random]
                // Se crea una nueva actividad con la cancion seleccionada
                runOnUiThread {
                    goNewSong(song)
                }
            }
        }
    }


    // Se crea una nueva actividad con la cancion seleccionada
    private fun goNewSong(song:SongsResultItem){
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(fileTag,song.file)
        intent.putExtra(songTag,song.title)
        intent.putExtra(imageTag,song.coverImage)
        intent.putExtra(authorTag,song.artist)
        startActivity(intent)
    }

}