package com.example.nytcleanarcitecture.utils

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: ImageView, url: String)
}
