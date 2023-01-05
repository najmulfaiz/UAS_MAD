package com.najmulfaiz.uas_mad

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najmulfaiz.uas_mad.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var listTip: RecyclerView
    private lateinit var tipAdapter: TipAdapter
    private lateinit var btnBack: ImageButton
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        token = preferences.getString("TOKEN", "").toString()

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        getTips();
    }

    private fun getTips() {
        api.get_tips("Bearer ${token}").enqueue(object :
            Callback<TipResponse> {
            override fun onFailure(call: Call<TipResponse>, t: Throwable) {
                Log.d("TipActivity", t.toString())
            }

            override fun onResponse(call: Call<TipResponse>, response: Response<TipResponse>) {
                if (response.isSuccessful){
                    val body = response.body()
                    var metadata: Metadata? = body?.metadata
                    var response: List<TipItem>? = body?.response
                    tipAdapter = TipAdapter(response, object: TipAdapter.OnAdapterListener {
                        override fun showDetail(data: TipItem?) {
                            val detailTipIntent = Intent(this@TipActivity, DetailTipActivity::class.java)
                                .putExtra("data", data)
                            startActivity(detailTipIntent)
                        }
                    })

                    listTip = findViewById(R.id.listTip)
                    listTip.apply {
                        layoutManager = LinearLayoutManager(this@TipActivity)
                        adapter = tipAdapter
                    }
                }
            }
        })
    }
}