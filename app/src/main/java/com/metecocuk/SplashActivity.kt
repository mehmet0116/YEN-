package com.metecocuk

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class SplashActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    
    private lateinit var textToSpeech: TextToSpeech
    private var isTtsReady = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        // Initialize TextToSpeech for welcome message
        textToSpeech = TextToSpeech(this, this)
        
        // Simulate loading process
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            if (isTtsReady) {
                speakWelcomeMessage()
            } else {
                // If TTS not ready, proceed anyway
                proceedToMainActivity()
            }
        }
        
        // Delay for splash screen
        handler.postDelayed(runnable, 3000)
    }
    
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale("tr", "TR"))
            
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Türkçe dili desteklenmiyor")
            } else {
                isTtsReady = true
                speakWelcomeMessage()
            }
        } else {
            Log.e("TTS", "TextToSpeech başlatılamadı")
        }
    }
    
    private fun speakWelcomeMessage() {
        val welcomeMessage = "Hoş geldin Mete! Senin için hazırladığımız eğlenceli oyunlara hoş geldin. Hadi birlikte öğrenelim ve eğlenelim!"
        
        textToSpeech.speak(
            "Merhaba Mete!",
            TextToSpeech.QUEUE_FLUSH,
            null,
            "welcome1"
        )
        
        textToSpeech.speak(
            welcomeMessage,
            TextToSpeech.QUEUE_ADD,
            null,
            "welcome2"
        )
        
        // Proceed to main activity after speaking
        Handler(Looper.getMainLooper()).postDelayed({
            proceedToMainActivity()
        }, 5000)
    }
    
    private fun proceedToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    
    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}