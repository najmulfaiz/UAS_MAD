package com.najmulfaiz.uas_mad

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvText: TextView
    private lateinit var btnLogout: Button
    private lateinit var btnServiceBooking: ImageButton
    private lateinit var btnPart: ImageButton
    private lateinit var btnEmergencyHotline: ImageButton
    private lateinit var btnTipsTrick: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tvText)
        btnLogout = findViewById(R.id.btnLogout)
        btnServiceBooking = findViewById(R.id.btnServiceBooking)
        btnPart = findViewById(R.id.btnPart)
        btnEmergencyHotline = findViewById(R.id.btnEmergencyHotline)
        btnTipsTrick = findViewById(R.id.btnTipsTrick)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        var token = preferences.getString("TOKEN", "")

        if(token == "") {
            val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        tvText.text = token
        btnLogout.setOnClickListener {
            preferences.edit().remove("TOKEN").commit();
            finish();
            startActivity(getIntent());
        }

        btnServiceBooking.setOnClickListener {
            val loginIntent = Intent(this@MainActivity, BookingActivity::class.java)
            startActivity(loginIntent)
        }
    }
}