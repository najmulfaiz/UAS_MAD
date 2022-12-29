package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName
import com.najmulfaiz.uas_mad.JenisKendaraanAdapter
import java.io.Serializable

data class BookingResponse(
    @field:SerializedName("metadata")
    var metadata : Metadata? = Metadata(),

    @field:SerializedName("response")
    val response: List<BookingItem>? = null
)

class BookingItem :Serializable {
    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("tanggal")
    val tanggal: String? = null

    @field:SerializedName("nopol")
    val nopol: String? = null

    @field:SerializedName("jenis_kendaraan")
    val jenis_kendaraan: JenisKendaraanItem? = JenisKendaraanItem()

    @field:SerializedName("jenis_transmisi")
    val jenis_transmisi: JenisTransmisiItem? = JenisTransmisiItem()

    @field:SerializedName("tahun")
    val tahun: String? = null

    @field:SerializedName("bahan_bakar")
    val bahan_bakar: String? = null

    @field:SerializedName("keluhan")
    val keluhan: String? = null
}