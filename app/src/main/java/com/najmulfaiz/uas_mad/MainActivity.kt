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
    private lateinit var tvUserNama: TextView
    private lateinit var btnLogout: Button
    private lateinit var btnServiceBooking: ImageButton
    private lateinit var btnPart: ImageButton
    private lateinit var btnEmergencyHotline: ImageButton
    private lateinit var btnTipsTrick: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvUserNama = findViewById(R.id.tvUserNama)
        btnLogout = findViewById(R.id.btnLogout)
        btnServiceBooking = findViewById(R.id.btnServiceBooking)
        btnPart = findViewById(R.id.btnPart)
        btnEmergencyHotline = findViewById(R.id.btnEmergencyHotline)
        btnTipsTrick = findViewById(R.id.btnTipsTrick)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        var token = preferences.getString("TOKEN", "")
        var user_nama = preferences.getString("USER_NAMA", "")

        if(token == "") {
            val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        tvUserNama.text = user_nama
        btnLogout.setOnClickListener {
            preferences.edit().remove("TOKEN").commit();
            finish();
            startActivity(getIntent());
        }

        btnServiceBooking.setOnClickListener {
            val bookingIntent = Intent(this@MainActivity, HistoryBookingActivity::class.java)
            startActivity(bookingIntent)
        }

        btnEmergencyHotline.setOnClickListener {
            val emergencyIntent = Intent(this@MainActivity, EmergencyActivity::class.java)
            startActivity(emergencyIntent)
        }
    }
}