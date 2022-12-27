package com.najmulfaiz.uas_mad.model

import com.google.gson.annotations.SerializedName

data class Metadata (
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)