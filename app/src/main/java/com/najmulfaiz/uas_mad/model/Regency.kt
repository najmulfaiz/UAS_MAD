package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName

data class RegencyResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<RegencyItem>? = null
)

data class RegencyItem (
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("province_id")
    val province_id: String? = null
)
