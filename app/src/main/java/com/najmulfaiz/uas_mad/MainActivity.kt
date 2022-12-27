package com.najmulfaiz.uas_mad

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tvText)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        var token = preferences.getString("TOKENS", "")

        Log.d("MainActivity", token.toString());
        tvText.text = token
    }
}