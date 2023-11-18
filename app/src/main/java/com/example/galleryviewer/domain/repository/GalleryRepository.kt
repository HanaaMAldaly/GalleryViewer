package com.example.galleryviewer.domain.repository

import com.example.galleryviewer.domain.model.ImageModel

interface GalleryRepository {
    suspend fun getGallery(
        page: Int,
        count: Int
    ): List<ImageModel>
}