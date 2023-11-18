package com.example.galleryviewer.data.imageLoader

import android.content.Context
import com.squareup.picasso.Picasso

object PicassoInitializer {
    lateinit var appContext: Context
    val instance by lazy {
        Picasso.Builder(appContext)
            .build()
    }
}
