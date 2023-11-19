package com.example.galleryviewer.data.network.cloud

import com.example.galleryviewer.data.network.builder.RetrofitFactory
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit

class GalleryAPIClientTest {
    @Test
    fun testGalleryClient() {
        val retrofit = mockk<Retrofit>(relaxed = true)
        val mockedAPI = mockk<GalleryAPI>(relaxed = true)
        every { retrofit.create(GalleryAPI::class.java) } returns mockedAPI
        mockkObject(RetrofitFactory)
        every { RetrofitFactory.getAPIClient() } returns retrofit

        GalleryAPIClient.getGalleryAPI()

        verify { retrofit.create(GalleryAPI::class.java) }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
