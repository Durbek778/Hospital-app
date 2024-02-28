package com.example.hospitalapplication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.hospitalapplication.databinding.ActivityArtBinding

class ArtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.navigationToArtDatailedd.setOnClickListener {
            val Intent = Intent(this, ArtDetailedActivity::class.java)
            startActivity(Intent)

        }
        binding.navigationToArtDatailedd.setOnClickListener {
            val Intent = Intent(this, ArtDetailedActivity::class.java)
            startActivity(Intent)
        }
        binding.navigationToArtDataileddd.setOnClickListener {
            val Intent = Intent(this, ArtDetailedActivity::class.java)
            startActivity(Intent)
        }
    }
}