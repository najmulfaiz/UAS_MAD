package com.najmulfaiz.uas_mad

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Part
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najmulfaiz.uas_mad.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var listPart: RecyclerView
    private lateinit var partAdapter: PartAdapter
    private lateinit var btnBack: ImageButton
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        token = preferences.getString("TOKEN", "").toString()

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        getParts();
    }

    private fun getParts() {
        api.get_parts("Bearer ${token}").enqueue(object :
            Callback<PartResponse> {
            override fun onFailure(call: Call<PartResponse>, t: Throwable) {
                Log.d("PartActivity", t.toString())
            }

            override fun onResponse(call: Call<PartResponse>, response: Response<PartResponse>) {
                if (response.isSuccessful){
                    val body = response.body()
                    var metadata: Metadata? = body?.metadata
                    var response: List<PartItem>? = body?.response
                    partAdapter = PartAdapter(response);

                    listPart = findViewById(R.id.listPart)
                    listPart.apply {
                        layoutManager = LinearLayoutManager(this@PartActivity)
                        adapter = partAdapter
                    }
                }
            }
        })
    }
}