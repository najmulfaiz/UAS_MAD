package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName
import com.najmulfaiz.uas_mad.JenisKendaraanAdapter
import java.io.Serializable

data class PartResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<PartItem>? = null
)

class PartItem :Serializable {
    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("nama")
    val nama: String? = null

    @field:SerializedName("harga")
    val harga: String? = null

    @field:SerializedName("image")
    val image: String? = null

    @field:SerializedName("part_category")
    val part_category: PartCategoryItem? = PartCategoryItem()
}