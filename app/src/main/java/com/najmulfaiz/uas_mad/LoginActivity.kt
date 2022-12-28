package com.najmulfaiz.uas_mad

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.najmulfaiz.uas_mad.model.Metadata
import com.najmulfaiz.uas_mad.model.UserAuth
import com.najmulfaiz.uas_mad.model.UserAuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        btnLogin.setOnClickListener {
            email = etEmail.text.toString()
            password = etPassword.text.toString()

            api.login(email, password).enqueue(object :
                Callback<UserAuthResponse> {
                override fun onFailure(call: Call<UserAuthResponse>, t: Throwable) {
                    Log.d("LoginActivity", t.toString())
                }

                override fun onResponse(call: Call<UserAuthResponse>, response: Response<UserAuthResponse>) {
                    if (response.isSuccessful){
                        val body = response.body()
                        var metadata: Metadata? = body?.metadata
                        var response: UserAuth? = body?.response

                        Toast.makeText(this@LoginActivity, metadata?.message, Toast.LENGTH_LONG).show()
                        if(metadata?.code == 200) {
                            val preferences: SharedPreferences = getSharedPreferences("UAS_MAD", MODE_PRIVATE)
                            preferences.edit().putString("TOKEN", response?.token).apply()

                            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(mainIntent)
                        }
                    }
                }
            })
        }

        tvRegister.setOnClickListener {
            val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}