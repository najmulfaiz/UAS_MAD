package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName
import com.najmulfaiz.uas_mad.JenisKendaraanAdapter
import java.io.Serializable

data class PartCategoryResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<PartItem>? = null
)

class PartCategoryItem :Serializable {
    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("nama")
    val nama: String? = null
}