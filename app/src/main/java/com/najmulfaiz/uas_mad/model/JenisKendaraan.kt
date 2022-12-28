package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName

data class JenisKendaraanResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<JenisKendaraanItem>? = null
)

data class JenisKendaraanItem (
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("nama")
    val nama: String? = null
)
