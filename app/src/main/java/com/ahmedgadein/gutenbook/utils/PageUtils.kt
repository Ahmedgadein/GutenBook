package com.ahmedgadein.gutenbook.utils

import android.net.Uri

fun getPage(url: String?): Int? {
    if (url == null) return null
    val uri = Uri.parse(url)
    return uri.getQueryParameter("page")?.toInt()!!
}
