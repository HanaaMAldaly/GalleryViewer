package com.example.galleryviewer.data.mapper

import com.example.galleryviewer.data.vo.GalleryResponse
import com.example.galleryviewer.domain.repository.model.ImageModel

fun GalleryResponse.map() = this.photos?.photo?.map {
    ImageModel(
        it.id ?: "",
        it.farm ?: 0,
        it.server ?: "",
        it.secret ?: ""
    )
}