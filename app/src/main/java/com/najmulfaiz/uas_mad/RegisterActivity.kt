package com.najmulfaiz.uas_mad

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.najmulfaiz.uas_mad.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etNoHandphone: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etPassword: EditText
    private lateinit var spProvince: Spinner
    private lateinit var spRegency: Spinner
    private lateinit var btnRegister: Button
    private var name: String = ""
    private var email: String = ""
    private var no_hp: String = ""
    private var alamat: String = ""
    private var province_id: String = ""
    private var regency_id: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        getProvinces();

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etNoHandphone = findViewById(R.id.etNoHandphone)
        etAlamat = findViewById(R.id.etAlamat)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            name = etName.text.toString()
            email = etEmail.text.toString()
            no_hp = etNoHandphone.text.toString()
            alamat = etAlamat.text.toString()
            password = etPassword.text.toString()

            api.register(name, email, no_hp, alamat, password, province_id, regency_id).enqueue(object : Callback<UserAuthResponse> {
                override fun onFailure(call: Call<UserAuthResponse>, t: Throwable) {
                    Log.d("MainActivity", t.toString())
                }

                override fun onResponse(call: Call<UserAuthResponse>, response: Response<UserAuthResponse>) {
                    if (response.isSuccessful){
                        val body = response.body()
                        var metadata: Metadata? = body?.metadata
                        var response: UserAuth? = body?.response

                        Toast.makeText(this@RegisterActivity, metadata?.message, Toast.LENGTH_LONG).show()
                        if(metadata?.code == 200) {
                            val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
                            preferences.edit().putString("TOKEN", response?.token).apply()

                            val mainIntent = Intent(this@RegisterActivity, MainActivity::class.java)
                            startActivity(mainIntent)
                        }
                    }
                }
            })
        }
    }

    private fun getProvinces() {
        api.get_provinces().enqueue(object : Callback<ProvinceResponse> {
            override fun onFailure(call: Call<ProvinceResponse>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }

            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                if (response.isSuccessful){
                    val provinces = response.body()?.response
                    var adapter: ProvinceAdapter = ProvinceAdapter(this@RegisterActivity, provinces!!)
                    spProvince = findViewById(R.id.spProvince)

                    spProvince.adapter = adapter

                    spProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            province_id = provinces[position].id.toString()
                            getRegencies(province_id)
                        }
                    }
                }
            }
        })
    }

    private fun getRegencies(province_id: String) {
        api.get_regencies(province_id).enqueue(object : Callback<RegencyResponse> {
            override fun onFailure(call: Call<RegencyResponse>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }

            override fun onResponse(call: Call<RegencyResponse>, response: Response<RegencyResponse>) {
                if (response.isSuccessful){
                    val regencies = response.body()?.response
                    var adapter: RegencyAdapter = RegencyAdapter(this@RegisterActivity, regencies!!)
                    spRegency = findViewById(R.id.spRegency)

                    spRegency.adapter = adapter

                    spRegency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            regency_id = regencies[position].id.toString()
                        }
                    }
                } else {
                    Log.e("RegisterActivity", "TEST")
                }
            }
        })
    }
}