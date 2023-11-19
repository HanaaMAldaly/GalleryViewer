package com.example.galleryviewer.data.mapper

import com.example.galleryviewer.data.vo.GalleryResponse
import com.example.galleryviewer.data.vo.PhotoResponse
import com.example.galleryviewer.data.vo.PhotosResponse
import com.example.galleryviewer.domain.model.ImageModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GalleryResponseMapperKtTest {
    @Test
    fun testGalleryResponseMapper() {
        val expectedResult = listOf(ImageModel("id", 5, "server", "secret"))
        val photosResponse = PhotosResponse(
            1,
            1,
            20,
            20,
            listOf(
                PhotoResponse(
                    "id",
                    null,
                    "secret",
                    "server",
                    5,
                    "",
                    null, null, null,
                ),
            ),
        )
        val galleryResponse = GalleryResponse(
            photosResponse,
            "",
        )

        assertEquals(expectedResult, galleryResponse.map())
    }
}
