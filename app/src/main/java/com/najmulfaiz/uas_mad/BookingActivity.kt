package com.najmulfaiz.uas_mad

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.najmulfaiz.uas_mad.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var etTanggal: EditText
    private lateinit var etNopol: EditText
    private lateinit var spJenisKendaraan: Spinner
    private lateinit var spJenisTransmisi: Spinner
    private lateinit var etTahun: EditText
    private lateinit var etBahanBakar: EditText
    private lateinit var etKeluhan: EditText
    private lateinit var btnSaveBooking: Button
    private var dateFormatter: SimpleDateFormat? = null
    private var tanggal: String = ""
    private var nopol: String = ""
    private var jenis_kendaraan_id: String = ""
    private var jenis_transmisi_id: String = ""
    private var tahun: String = ""
    private var bahan_bakar: String = ""
    private var keluhan: String = ""
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        etTanggal = findViewById(R.id.etTanggal)
        etNopol = findViewById(R.id.etNopol)
        etTahun = findViewById(R.id.etTahun)
        etBahanBakar = findViewById(R.id.etBahanBakar)
        etKeluhan = findViewById(R.id.etKeluhan)
        btnSaveBooking = findViewById(R.id.btnSaveBooking)

        val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
        token = preferences.getString("TOKEN", "").toString()

        getJenisKendaraan()
        getJenisTransmisi()

        btnSaveBooking.setOnClickListener {
            tanggal = etTanggal.text.toString()
            nopol = etNopol.text.toString()
            tahun = etTahun.text.toString()
            bahan_bakar = etBahanBakar.text.toString()
            keluhan = etKeluhan.text.toString()

            api.post_booking("Bearer ${token}", tanggal, nopol, jenis_kendaraan_id, jenis_transmisi_id, tahun, bahan_bakar, keluhan).enqueue(object : Callback<BookingResponse> {
                override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                    Log.d("MainActivity", t.toString())
                }

                override fun onResponse(call: Call<BookingResponse>, response: Response<BookingResponse>) {
                    if (response.isSuccessful){
                        val body = response.body()
                        var metadata: Metadata? = body?.metadata

                        Toast.makeText(this@BookingActivity, metadata?.message, Toast.LENGTH_LONG).show()
                        if(metadata?.code == 200) {
                            //

                            val mainIntent = Intent(this@BookingActivity, MainActivity::class.java)
                            startActivity(mainIntent)
                        }
                    }
                }
            })
        }
    }

    fun showTanggal(view: View) {
        val mCalendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                mCalendar.set(Calendar.YEAR, year)
                mCalendar.set(Calendar.MONTH, monthOfYear)
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                etTanggal.setText(dateFormatter?.format(mCalendar.time).toString())
            },
            mCalendar.get(Calendar.YEAR),
            mCalendar.get(Calendar.MONTH),
            mCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun getJenisKendaraan() {
        api.get_jenis_kendaraan("Bearer ${token}").enqueue(object : Callback<JenisKendaraanResponse> {
            override fun onFailure(call: Call<JenisKendaraanResponse>, t: Throwable) {
                Log.d("BookingActivity", t.toString())
            }

            override fun onResponse(call: Call<JenisKendaraanResponse>, response: Response<JenisKendaraanResponse>) {
                if (response.isSuccessful){
                    val jenis_kendaraan = response.body()?.response
                    var adapter: JenisKendaraanAdapter = JenisKendaraanAdapter(this@BookingActivity, jenis_kendaraan!!)
                    spJenisKendaraan = findViewById(R.id.spJenisKendaraan)

                    spJenisKendaraan.adapter = adapter

                    spJenisKendaraan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            jenis_kendaraan_id = jenis_kendaraan[position].id.toString()
                        }
                    }
                }
            }
        })
    }

    private fun getJenisTransmisi() {
        api.get_jenis_transmisi("Bearer ${token}").enqueue(object : Callback<JenisTransmisiResponse> {
            override fun onFailure(call: Call<JenisTransmisiResponse>, t: Throwable) {
                Log.d("BookingActivity", t.toString())
            }

            override fun onResponse(call: Call<JenisTransmisiResponse>, response: Response<JenisTransmisiResponse>) {
                if (response.isSuccessful){
                    val jenis_transmisi = response.body()?.response
                    var adapter: JenisTransmisiAdapter = JenisTransmisiAdapter(this@BookingActivity, jenis_transmisi!!)
                    spJenisTransmisi = findViewById(R.id.spJenisTransmisi)

                    spJenisTransmisi.adapter = adapter

                    spJenisTransmisi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            jenis_transmisi_id = jenis_transmisi[position].id.toString()
                        }
                    }
                }
            }
        })
    }
}