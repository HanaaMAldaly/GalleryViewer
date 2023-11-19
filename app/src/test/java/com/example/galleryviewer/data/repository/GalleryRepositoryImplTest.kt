package com.example.galleryviewer.data.repository

import com.example.galleryviewer.data.network.cloud.GalleryAPI
import com.example.galleryviewer.data.network.cloud.GalleryAPIClient
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

class GalleryRepositoryImplTest {
    @Test
    fun testGalleryRepositoryListItems() {
        val page = 1
        val count = 20
        val mockedAPI = mockk<GalleryAPI>(relaxed = true)
        mockkObject(GalleryAPIClient)
        every { GalleryAPIClient.getGalleryAPI() } returns mockedAPI
        val repositoryImpl = GalleryRepositoryImpl()

        runTest {
            repositoryImpl.getGallery(page, count)
        }

        coVerify {
            mockedAPI.getGallery(page, count)
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
