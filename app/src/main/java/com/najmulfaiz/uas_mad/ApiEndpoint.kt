package com.najmulfaiz.uas_mad

import com.najmulfaiz.uas_mad.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("referensi/provinces")
    fun get_provinces() : Call<ProvinceResponse>

    @GET("referensi/regencies")
    fun get_regencies(@Query("province_id") province_id: String?) : Call<RegencyResponse>

    @GET("jenis_kendaraan")
    fun get_jenis_kendaraan(@Header("Authorization") token: String) : Call<JenisKendaraanResponse>

    @GET("jenis_transmisi")
    fun get_jenis_transmisi(@Header("Authorization") token: String) : Call<JenisTransmisiResponse>

    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("no_hp") no_hp: String,
        @Field("alamat") alamat: String,
        @Field("password") password: String,
        @Field("province_id") province_id: String,
        @Field("regency_id") regency_id: String
    ) : Call<UserAuthResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<UserAuthResponse>

    @GET("user")
    fun get_user() : Call<UserAuth>

    @GET("booking")
    fun get_booking(@Header("Authorization") token: String) : Call<BookingResponse>

    @FormUrlEncoded
    @POST("booking")
    fun post_booking(
        @Header("Authorization") token: String,
        @Field("tanggal") tanggal: String,
        @Field("nopol") nopol: String,
        @Field("jenis_kendaraan_id") jenis_kendaraan_id: String,
        @Field("jenis_transmisi_id") jenis_transmisi_id: String,
        @Field("tahun") tahun: String,
        @Field("bahan_bakar") bahan_bakar: String,
        @Field("keluhan") keluhan: String
    ) : Call<BookingResponse>

    @GET("booking/{booking_id}")
    fun get_booking_detail(@Header("Authorization") token: String, @Path("booking_id") booking_id: String) : Call<BookingResponse>

    @GET("part")
    fun get_parts(@Header("Authorization") token: String) : Call<PartResponse>

    @GET("tip")
    fun get_tips(@Header("Authorization") token: String) : Call<TipResponse>
}