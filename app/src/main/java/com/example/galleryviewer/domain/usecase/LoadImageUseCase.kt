package com.example.galleryviewer.domain.usecase

import android.widget.ImageView
import com.example.galleryviewer.data.imageLoader.PicassoInitializer

class LoadImageUseCase:(String, ()->ImageView)->Unit {
    override fun invoke(path: String, imageCallback: () -> ImageView) {
        PicassoInitializer.instance.load(path)
            .fit()
            .into(imageCallback())
    }
}