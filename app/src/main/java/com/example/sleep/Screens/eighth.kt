package com.example.sleep.Screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sleep.R

class eighth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eighth)
        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val userUid = sharedPref.getString("user_uid", null)
        if (userUid != null) {

        } else {
            Log.e("eighth", "UID не найден в SharedPreferences")
        }
        val moondefault = findViewById<ImageView>(R.id.moon_default)
        moondefault.setOnClickListener {
            val intent = Intent(
                this@eighth,
                fourth_1::class.java
            )
            startActivity(intent)
        }

        val chartBoldDefault = findViewById<ImageView>(R.id.chart_bold_default)
        chartBoldDefault.setOnClickListener {
            val intent = Intent(
                this@eighth,
                fifth::class.java
            )
            startActivity(intent)
        }

        val lotusdefault = findViewById<ImageView>(R.id.lotus_default)
        lotusdefault.setOnClickListener {
            val intent = Intent(
                this@eighth,
                seventh_1::class.java
            )
            startActivity(intent)
        }

        val exitLayout = findViewById<LinearLayout>(R.id.exit)
        exitLayout.setOnClickListener {
            val intent = Intent(
                this@eighth,
                Login::class.java
            )
            startActivity(intent)
        }
        val sleepsettings = findViewById<LinearLayout>(R.id.settings)
        sleepsettings.setOnClickListener {
            val intent = Intent(
                this@eighth,
                third_2::class.java
            )
            startActivity(intent)
        }
    }
}