package com.example.galleryviewer

import android.app.Application
import com.example.galleryviewer.data.imageLoader.PicassoInitializer

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        PicassoInitializer.appContext = applicationContext
    }
}