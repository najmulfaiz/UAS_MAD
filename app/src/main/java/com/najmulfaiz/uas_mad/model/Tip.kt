package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName
import com.najmulfaiz.uas_mad.JenisKendaraanAdapter
import java.io.Serializable

data class TipResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<PartItem>? = null
)

class TipItem :Serializable {
    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("bahan_bakar")
    val bahan_bakar: String? = null

    @field:SerializedName("keluhan")
    val keluhan: String? = null
}