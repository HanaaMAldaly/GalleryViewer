package com.example.galleryviewer.domain.usecase

import com.example.galleryviewer.domain.model.ImageModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ImagePathUseCaseTest {

    @Test
    fun testImagePathUseCase_expectReturnImagePath() {
        val id = "id"
        val fame = 5
        val server = "server"
        val secret = "secret"
        val image = ImageModel(id, fame, server, secret)
        val expectedResult =
            "http://farm${image.farm}.static.flickr.com/${image.server}/${image.id}_${image.secret}.jpg"

        val useCase = ImagePathUseCase()

        assertEquals(expectedResult, useCase.invoke(image))
    }
}
