package com.najmulfaiz.uas_mad

import com.najmulfaiz.uas_mad.model.ProvinceResponse
import com.najmulfaiz.uas_mad.model.RegencyResponse
import com.najmulfaiz.uas_mad.model.UserAuth
import com.najmulfaiz.uas_mad.model.UserAuthResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("referensi/provinces")
    fun get_provinces() : Call<ProvinceResponse>

    @GET("referensi/regencies")
    fun get_regencies(@Query("province_id") province_id: String?) : Call<RegencyResponse>

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
    ) : Call<UserAuth>

    @GET("user")
    fun get_user() : Call<UserAuth>
}