package com.example.hospitalapplication

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GameActivity : AppCompatActivity() {

    var utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar?.hide()

        lifecycleScope.launch {
            supabase.getGames()
            val linearLayout1 = findViewById<View>(R.id.images_container) as LinearLayout

            for (x in 0 until supabase.games.size) {
                withContext(Dispatchers.Main) {
                    val image =
                        ImageView(this@GameActivity)
                    utils.getImageBitmap(supabase.games[x].gameImgSrc) { bitmap ->
                        image.setImageBitmap(bitmap as Bitmap?)
                    }
                    image.setOnClickListener {
                        val url = supabase.games[x].gamesrc
                        openUrlInBrowser(url)
                    }
                    linearLayout1.addView(image)
                }
            }
        }


    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

//        val subwaysurfers = findViewById<ImageView>(R.id.subwaysurfers)
//        subwaysurfers.setOnClickListener {
//            val url = "https://poki.com/kr/g/subway-surfers"
//            openUrlInBrowser(url)
//        }
//
//        val colorSort = findViewById<ImageView>(R.id.colorSort)
//        colorSort.setOnClickListener {
//            val url = "https://poki.com/en/g/water-color-sort"
//            openUrlInBrowser(url)
//        }
//
//        val onetMaster = findViewById<ImageView>(R.id.onetMaster)
//        onetMaster.setOnClickListener {
//            val url = "https://poki.com/en/g/onet-master"
//            openUrlInBrowser(url)
//        }