package com.najmulfaiz.uas_mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.najmulfaiz.uas_mad.model.TipItem

class DetailTipActivity : AppCompatActivity() {
    private lateinit var ivTipDetail: ImageView
    private lateinit var tvTipTitle: TextView
    private lateinit var tvTipContent: TextView
    private lateinit var btnBack: ImageButton
    private val data by lazy {
        intent.getSerializableExtra("data") as TipItem?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tip)

        ivTipDetail = findViewById(R.id.ivTipDetail)
        tvTipTitle = findViewById(R.id.tvTipTitle)
        tvTipContent = findViewById(R.id.tvTipContent)
        btnBack = findViewById(R.id.btnBack)

        Glide.with(this).load(data?.image).into(ivTipDetail);
        tvTipTitle.text = data?.title
        tvTipContent.text = data?.content

        btnBack.setOnClickListener {
            finish()
        }
    }
}