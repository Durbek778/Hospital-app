package com.example.hospitalapplication

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hospitalapplication.adapters.ImageAdapter
import com.example.hospitalapplication.models.ImageItem
import java.util.*

class ArtDetailedActivity : AppCompatActivity() {
    private lateinit var scrollView: ScrollView
    private lateinit var viewpager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

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

        scrollView = findViewById<ScrollView>(R.id.sv_wrapper)

        // Set a global layout listener to scroll to the bottom after the view is drawn
        scrollView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                scrollView.fullScroll(View.FOCUS_DOWN)
                scrollView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        // Optionally, you can use a Handler for a smooth scrolling effect
        val handler = Handler()
        handler.postDelayed({
            scrollView.fullScroll(View.FOCUS_DOWN)
        }, 7000) // Delay in milliseconds


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

        val imageAdapter = ImageAdapter()
        viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

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