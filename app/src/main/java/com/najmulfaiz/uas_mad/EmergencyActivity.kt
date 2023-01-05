package com.najmulfaiz.uas_mad

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class EmergencyActivity : AppCompatActivity() {
    private lateinit var btnToWhatsapp: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)

        btnToWhatsapp = findViewById(R.id.btnToWhatsapp)
        btnToWhatsapp.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=6282213933187"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}