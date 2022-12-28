package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName
import com.najmulfaiz.uas_mad.model.Metadata

data class BookingResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<UserItem>? = null
)

data class BookingItem (
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("no_hp")
    val no_hp: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("province_id")
    val province_id: String? = null,

    @field:SerializedName("regency_id")
    val regency_id: String? = null,

    @field:SerializedName("province")
    val province: ProvinceItem? = ProvinceItem(),

    @field:SerializedName("regency")
    val regency: RegencyItem? = RegencyItem()
)
