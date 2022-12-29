package com.najmulfaiz.uas_mad

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najmulfaiz.uas_mad.model.BookingItem
import com.najmulfaiz.uas_mad.model.BookingResponse
import com.najmulfaiz.uas_mad.model.Metadata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryBookingActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var listBooking: RecyclerView
    private lateinit var bookingAdapter: BookingAdapter
    private lateinit var btnBooking: Button
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_booking)

        btnBooking = findViewById(R.id.btnBooking)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        token = preferences.getString("TOKEN", "").toString()

        btnBooking.setOnClickListener {
            val bookingIntent = Intent(this@HistoryBookingActivity, BookingActivity::class.java)
            startActivity(bookingIntent)
        }

        getBooking();
    }

    private fun getBooking() {
        api.get_booking("Bearer ${token}").enqueue(object :
            Callback<BookingResponse> {
            override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                Log.d("HistoryBookingActivity", t.toString())
            }

            override fun onResponse(call: Call<BookingResponse>, response: Response<BookingResponse>) {
                if (response.isSuccessful){
                    val body = response.body()
                    var metadata: Metadata? = body?.metadata
                    var response: List<BookingItem>? = body?.response
                    bookingAdapter = BookingAdapter(response);

                    listBooking = findViewById(R.id.listBooking)
                    listBooking.apply {
                        layoutManager = LinearLayoutManager(this@HistoryBookingActivity)
                        adapter = bookingAdapter
                    }
                }
            }
        })
    }
}