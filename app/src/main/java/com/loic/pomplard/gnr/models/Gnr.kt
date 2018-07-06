package com.loic.pomplard.gnr.models

import android.graphics.drawable.Drawable

data class Gnr(
        val title: String = "",
        val description: String = "",
        val image: Drawable,
        val srcPdf: String = "",
        var isInLocal: Boolean = false)
