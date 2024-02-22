package com.example.hospitalapplication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

lateinit var supabase: Requests


class MainActivity : AppCompatActivity() {
    lateinit var pulse_text: TextView
    lateinit var pills_text: TextView
    lateinit var infusion_text: TextView
    suspend fun initSupabase() {
        supabase = Requests()
    }

    fun updatePatientInfo() {
        pulse_text.text = supabase.patients.heartRate.toString() + " BPM";
        infusion_text.text = supabase.patients.bloodGlucose.toString() + " BPM";
        pills_text.text = supabase.patients.bloodPressure.toString() + " BPM";
    }

    fun initUI() {
        pulse_text = findViewById(R.id.pulse_text)
        infusion_text = findViewById(R.id.infusion_text)
        pills_text = findViewById(R.id.pills_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

        lifecycleScope.launch(handler) {
            initSupabase()
            supabase.getPatientInfo()
            updatePatientInfo()

        }

        supportActionBar?.hide()

        val navigationProfile = findViewById<LinearLayout>(R.id.navigation_to_ProfileEditAcv)
        navigationProfile.setOnClickListener {
            val Intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(Intent)

        }

        val navigationGame = findViewById<LinearLayout>(R.id.navigation_to_game)
        navigationGame.setOnClickListener {
            val Intent = Intent(this, GameActivity::class.java)
            startActivity(Intent)

        }

        val navigationArt = findViewById<LinearLayout>(R.id.navigation_to_artAct)
        navigationArt.setOnClickListener {
            val Intent = Intent(this, ArtActivity::class.java)
            startActivity(Intent)

        }

        val navigationMedia = findViewById<LinearLayout>(R.id.navigation_to_mediaAct)
        navigationMedia.setOnClickListener {
            val Intent = Intent(this, MediaActivity::class.java)
            startActivity(Intent)

        }
    }


}