package com.example.galleryviewer.domain.repository

import com.example.galleryviewer.domain.repository.model.Image

interface GalleryRepository {
    suspend fun getGallery() : List<Image>
}