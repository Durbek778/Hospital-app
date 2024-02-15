package com.example.hospitalapplication

import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hospitalapplication.adapters.ImageAdapter
import com.example.hospitalapplication.models.ImageItem
import java.util.*

class ArtDetailedActivity : AppCompatActivity() {
    private lateinit var viewpager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val autoScrollHandler = Handler()
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val currentItem = viewpager2.currentItem
            viewpager2.setCurrentItem((currentItem + 1) % imageList.size, true)
            autoScrollHandler.postDelayed(this, AUTO_SCROLL_INTERVAL)
        }
    }

    private companion object {
        const val AUTO_SCROLL_INTERVAL =
            2500L // Auto-scroll interval in milliseconds (adjust as needed)
    }

    val imageList = arrayListOf(
        ImageItem(
            UUID.randomUUID().toString(),
            R.drawable.korean_art
        ),
        ImageItem(
            UUID.randomUUID().toString(),
            R.drawable.korean_art
        ),
        ImageItem(
            UUID.randomUUID().toString(),
            R.drawable.korean_art
        ),

        )

    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScroll()
    }

    private fun startAutoScroll() {
        autoScrollHandler.postDelayed(autoScrollRunnable, AUTO_SCROLL_INTERVAL)
    }

    private fun stopAutoScroll() {
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
    }

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_art_detailed)

        supportActionBar?.hide()

        viewpager2 = findViewById<ViewPager2>(R.id.viewpager2)

        val scrollView = findViewById<ScrollView>(R.id.sv_wrapper)
        val textView = findViewById<TextView>(R.id.tv_lorem) // Replace with your TextView's id

        // Make the TextView scrollable
        textView.movementMethod = ScrollingMovementMethod.getInstance()
        textView.isVerticalScrollBarEnabled = true

        // Make the ScrollView scrollable
        scrollView.isVerticalScrollBarEnabled = true


        val imageAdapter = ImageAdapter()
        viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        viewpager2.setPageTransformer { page, position ->
            val absPosition = Math.abs(position)
            page.alpha = 1f - absPosition
            page.scaleY = 0.85f + 0.15f * (1f - absPosition)
            page.translationX =
                -position * page.width / 4  // Adjust the speed by changing the denominator
        }

        val slideDotLL = findViewById<LinearLayout>(R.id.slideDotLL)
        val dotsImage = Array(imageList.size) { ImageView(this) }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slideDotLL.addView(it, params)
        }

        // default first dot selected
        dotsImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position == index) {
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    } else {
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }
        viewpager2.registerOnPageChangeCallback(pageChangeListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewpager2.unregisterOnPageChangeCallback(pageChangeListener)
    }

}