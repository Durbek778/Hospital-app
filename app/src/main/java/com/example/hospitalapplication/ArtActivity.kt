package com.example.hospitalapplication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ArtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_art)

        supportActionBar?.hide()

        val navigationDatailedArt = findViewById<LinearLayout>(R.id.navigation_to_Art_Datailed)
        navigationDatailedArt.setOnClickListener {
            val Intent = Intent(this, ArtDetailedActivity::class.java)
            startActivity(Intent)

        }

        val navigationDatailedArtt = findViewById<LinearLayout>(R.id.navigation_to_Art_Datailedd)
        navigationDatailedArtt.setOnClickListener {
            val Intent = Intent(this, ArtDetailedActivity::class.java)
            startActivity(Intent)

        }

        val navigationDatailedArttt = findViewById<LinearLayout>(R.id.navigation_to_Art_Dataileddd)
        navigationDatailedArttt.setOnClickListener {
            val Intent = Intent(this, ArtDetailedActivity::class.java)
            startActivity(Intent)

        }
    }
}