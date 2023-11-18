package com.example.galleryviewer.domain.usecase

import com.example.galleryviewer.domain.model.ImageModel

class ImagePathUseCase() : (ImageModel) -> String {
    override fun invoke(image: ImageModel): String {
        return "http://farm${image.farm}.static.flickr.com/${image.server}/${image.id}_${image.secret}.jpg"
    }
}
