package com.example.hospitalapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        lifecycleScope.launch {
            supabase.getGames()
        }

        supportActionBar?.hide()

        val subwaysurfers = findViewById<ImageView>(R.id.subwaysurfers)
        subwaysurfers.setOnClickListener {
            val url = "https://poki.com/kr/g/subway-surfers"
            openUrlInBrowser(url)
        }

        val colorSort = findViewById<ImageView>(R.id.colorSort)
        colorSort.setOnClickListener {
            val url = "https://poki.com/en/g/water-color-sort"
            openUrlInBrowser(url)
        }

        val onetMaster = findViewById<ImageView>(R.id.onetMaster)
        onetMaster.setOnClickListener {
            val url = "https://poki.com/en/g/onet-master"
            openUrlInBrowser(url)
        }

    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
