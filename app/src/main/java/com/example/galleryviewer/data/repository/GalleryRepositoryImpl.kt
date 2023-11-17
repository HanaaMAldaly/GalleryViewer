package com.example.galleryviewer.data.repository

import com.example.galleryviewer.domain.repository.GalleryRepository
import com.example.galleryviewer.domain.repository.model.Image

class GalleryRepositoryImpl : GalleryRepository {
    override suspend fun getGallery(): List<Image> {
        TODO("Not yet implemented")
    }
}