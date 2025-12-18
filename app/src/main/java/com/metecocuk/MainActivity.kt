package com.metecocuk

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var gamesRecyclerView: RecyclerView
    private lateinit var bottomNavView: BottomNavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize TextToSpeech
        textToSpeech = TextToSpeech(this, this)
        
        // Setup RecyclerView for games
        setupGamesRecyclerView()
        
        // Setup Bottom Navigation
        setupBottomNavigation()
    }
    
    private fun setupGamesRecyclerView() {
        gamesRecyclerView = findViewById(R.id.gamesRecyclerView)
        
        val gamesList = listOf(
            GameItem(
                R.drawable.ic_games,
                getString(R.string.game_colors),
                "Renkleri eğlenerek öğren",
                R.color.color_red
            ),
            GameItem(
                R.drawable.ic_learning,
                getString(R.string.game_shapes),
                "Şekilleri tanı ve eşleştir",
                R.color.color_blue
            ),
            GameItem(
                R.drawable.ic_games,
                getString(R.string.game_numbers),
                "1'den 10'a kadar saymayı öğren",
                R.color.color_green
            ),
            GameItem(
                R.drawable.ic_stories,
                getString(R.string.game_animals),
                "Hayvanları ve seslerini keşfet",
                R.color.color_orange
            ),
            GameItem(
                R.drawable.ic_games,
                getString(R.string.game_puzzle),
                "Resimleri birleştir, puzzle çöz",
                R.color.color_purple
            )
        )
        
        val adapter = GamesAdapter(gamesList) { game ->
            onGameClicked(game)
        }
        
        gamesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        gamesRecyclerView.adapter = adapter
    }
    
    private fun setupBottomNavigation() {
        bottomNavView = findViewById(R.id.navView)
        
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    speakMessage("Ana sayfadasın Mete")
                    true
                }
                R.id.navigation_games -> {
                    speakMessage("Oyunlar bölümüne hoş geldin")
                    showToast("Oyunlar bölümü yakında eklenecek")
                    true
                }
                R.id.navigation_learning -> {
                    speakMessage("Öğrenme zamanı! Hadi birlikte öğrenelim")
                    showToast("Öğrenme aktiviteleri yakında eklenecek")
                    true
                }
                R.id.navigation_music -> {
                    speakMessage("Müzik zamanı! Şarkı söyleyelim")
                    showToast("Müzik bölümü yakında eklenecek")
                    true
                }
                R.id.navigation_stories -> {
                    speakMessage("Hikaye zamanı! Dinleyelim ve öğrenelim")
                    showToast("Hikayeler yakında eklenecek")
                    true
                }
                else -> false
            }
        }
    }
    
    private fun onGameClicked(game: GameItem) {
        val message = "${game.title} oyununu seçtin. Hadi oynayalım!"
        speakMessage(message)
        showToast("${game.title} başlatılıyor...")
        
        // Here you would normally start the specific game activity
        // For now, just show a message
    }
    
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale("tr", "TR"))
            
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Türkçe dili desteklenmiyor")
            }
        } else {
            Log.e("TTS", "TextToSpeech başlatılamadı")
        }
    }
    
    private fun speakMessage(message: String) {
        if (::textToSpeech.isInitialized) {
            textToSpeech.speak(
                message,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "message"
            )
        }
    }
    
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
    
    data class GameItem(
        val iconResId: Int,
        val title: String,
        val description: String,
        val colorResId: Int
    )
}