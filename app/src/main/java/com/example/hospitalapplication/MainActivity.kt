package com.example.hospitalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val navigationProfile = findViewById<LinearLayout>(R.id.navigation_to_ProfileEditAcv)
        navigationProfile.setOnClickListener{
            val Intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(Intent)

        }

        val navigationGame = findViewById<LinearLayout>(R.id.navigation_to_game)
        navigationGame.setOnClickListener{
            val Intent = Intent(this, GameActivity::class.java)
            startActivity(Intent)

        }

        val navigationArt = findViewById<LinearLayout>(R.id.navigation_to_artAct)
        navigationArt.setOnClickListener{
            val Intent = Intent(this, ArtActivity::class.java)
            startActivity(Intent)

        }

        val navigationMedia= findViewById<LinearLayout>(R.id.navigation_to_mediaAct)
        navigationMedia.setOnClickListener{
            val Intent = Intent(this, MediaActivity::class.java)
            startActivity(Intent)

        }
    }
}