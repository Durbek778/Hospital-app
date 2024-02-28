package com.example.hospitalapplication

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
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
                    // Create ImageView and TextView dynamically for each game
                    val image = ImageView(this@GameActivity)
                    val textView = TextView(this@GameActivity)
                    val space = Space(this@GameActivity)

                    // Define fixed dimensions for the ImageView
                    val imageWidth =
                        resources.getDimensionPixelSize(R.dimen.image_width) // Define in dimens.xml
                    val imageHeight =
                        resources.getDimensionPixelSize(R.dimen.image_height) // Define in dimens.xml

                    // Set the ImageView's layout parameters to the fixed dimensions
                    image.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight).apply {
                        gravity = Gravity.CENTER
                    }

                    // Load and set image using utils.getImageBitmap
                    utils.getImageBitmap(supabase.games[x].gameImgSrc) { bitmap ->
                        image.setImageBitmap(bitmap as Bitmap?)
                    }

                    // Set click listener for image to open URL in browser
                    image.setOnClickListener {
                        val url = supabase.games[x].gamesrc
                        openUrlInBrowser(url)
                    }

                    // Set text for TextView from supabase.games[x].game_name
                    textView.text = supabase.games[x].game_name

                    // Add both ImageView and TextView to the linearLayout1
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        gravity = Gravity.CENTER
                    }

                    linearLayout1.addView(image, layoutParams)
                    linearLayout1.addView(textView, layoutParams)
                }
            }


        }


    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

