package com.example.galleryviewer.data.repository

import com.example.galleryviewer.data.mapper.map
import com.example.galleryviewer.data.network.cloud.GalleryAPIClient
import com.example.galleryviewer.domain.repository.GalleryRepository
import com.example.galleryviewer.domain.model.ImageModel

class GalleryRepositoryImpl : GalleryRepository {
    override suspend fun getGallery(
        page: Int,
        count: Int
    ): List<ImageModel> {
        return GalleryAPIClient.getGalleryAPI().getGallery(page, count,).map() ?: listOf()
    }
}