package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName
import com.najmulfaiz.uas_mad.JenisKendaraanAdapter
import java.io.Serializable

data class TipResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<TipItem>? = null
)

class TipItem :Serializable {
    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("title")
    val title: String? = null

    @field:SerializedName("content")
    val content: String? = null

    @field:SerializedName("image")
    val image: String? = null
}