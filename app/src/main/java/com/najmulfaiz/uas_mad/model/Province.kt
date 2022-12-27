package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<ProvinceItem>? = null
)

data class ProvinceItem (
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
