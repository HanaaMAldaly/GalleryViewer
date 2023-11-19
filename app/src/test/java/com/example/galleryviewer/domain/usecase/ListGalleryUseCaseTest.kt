package com.example.galleryviewer.domain.usecase

import com.example.galleryviewer.domain.repository.GalleryRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

class ListGalleryUseCaseTest {
    @Test
    fun testListGalleryUseCase_expectInvokeGalleryRepositoryListGallery() {
        val page = 1
        val count = 20
        val repository = mockk<GalleryRepository>()
        coEvery { repository.getGallery(page, count) } returns listOf()
        val useCase = ListGalleryUseCase(repository)

        runTest { useCase.invoke(page, count) }

        coVerify { repository.getGallery(page, count) }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
