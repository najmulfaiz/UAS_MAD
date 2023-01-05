package com.najmulfaiz.uas_mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.najmulfaiz.uas_mad.model.BookingResponse
import com.najmulfaiz.uas_mad.model.Metadata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingSuccessActivity : AppCompatActivity() {
    private lateinit var btnToHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_success)

        btnToHome = findViewById(R.id.btnToHome)

        btnToHome.setOnClickListener {
            val mainIntent = Intent(this@BookingSuccessActivity, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}