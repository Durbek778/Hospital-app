package com.example.hospitalapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        val bt_video1 = findViewById<Button>(R.id.bt_video1)
        val bt_video2 = findViewById<Button>(R.id.bt_video2)
        val bt_video3 = findViewById<Button>(R.id.bt_video3)
        val bt_video4 = findViewById<Button>(R.id.bt_video4)

        bt_video1.setOnClickListener { playVideo(R.raw.avatar) }
        bt_video2.setOnClickListener { playVideo(R.raw.iternals) }
        bt_video3.setOnClickListener { playVideo(R.raw.openhaimer) }
        bt_video4.setOnClickListener { playVideo(R.raw.video_napaleon) }
    }

    private fun playVideo(resourceId: Int) {
        val intent = Intent(this, WatchActivity::class.java)
        intent.putExtra(WatchActivity.EXTRA_VIDEO_RESOURCE_ID, resourceId)
        startActivity(intent)
    }
}
