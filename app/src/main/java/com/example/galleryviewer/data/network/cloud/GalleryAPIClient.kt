package com.example.galleryviewer.data.network.cloud

import com.example.galleryviewer.data.network.builder.RetrofitFactory

object GalleryAPIClient {
    fun getGalleryAPI() = RetrofitFactory.getAPIClient().create(GalleryAPI::class.java)
}
