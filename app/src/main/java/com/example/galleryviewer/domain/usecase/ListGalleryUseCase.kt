package com.example.galleryviewer.domain.usecase

import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.repository.GalleryRepository

class ListGalleryUseCase(private val repository: GalleryRepository) : suspend (Int, Int) -> List<ImageModel>{
    override suspend fun invoke(page: Int, count: Int): List<ImageModel> {
        return repository.getGallery(page, count)
    }
}
