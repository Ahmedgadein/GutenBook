package com.ahmedgadein.gutenbook.data.models


import com.google.gson.annotations.SerializedName

data class Formats(
    @SerializedName("application/zip")
    val applicationzip: String?,
    @SerializedName("image/jpeg")
    val imagejpeg: String?,
    @SerializedName("text/html")
    val texthtml: String?,
    @SerializedName("text/plain")
    val textplain: String?,
    @SerializedName("text/plain; charset=us-ascii")
    val textplainCharsetusAscii: String?
)