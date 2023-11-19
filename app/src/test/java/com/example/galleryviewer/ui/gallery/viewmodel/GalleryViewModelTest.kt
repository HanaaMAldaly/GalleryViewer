package com.example.galleryviewer.ui.gallery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.usecase.ImagePathUseCase
import com.example.galleryviewer.domain.usecase.ListGalleryUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test

class GalleryViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Test
    fun testListGallery() {
        val items = listOf(mockk<ImageModel>())
        val useCase = mockk<ListGalleryUseCase>()
        val pathUseCase = mockk<ImagePathUseCase>()
        coEvery { useCase.invoke(1, 20) } returns items
        val viewModel = GalleryViewModel(useCase, pathUseCase, testDispatcher)

        assertEquals(items, viewModel.gallery.value)
    }

    @Test
    fun testListGalleryPagination() {
        val items = listOf(mockk<ImageModel>())
        val useCase = mockk<ListGalleryUseCase>()
        val pathUseCase = mockk<ImagePathUseCase>()
        coEvery { useCase.invoke(1, 20) } returns items
        coEvery { useCase.invoke(2, 20) } returns items

        val viewModel = GalleryViewModel(useCase, pathUseCase, testDispatcher)

        viewModel.paginateCallbacks.onLoadMore()

        assertEquals(items + items, viewModel.gallery.value)
    }

    @Test
    fun testHasLoadedAllItems() {
        val useCase = mockk<ListGalleryUseCase>()
        val pathUseCase = mockk<ImagePathUseCase>()
        coEvery { useCase.invoke(1, 20) } returns emptyList()

        val viewModel = GalleryViewModel(useCase, pathUseCase, testDispatcher)

        assertEquals(true, viewModel.paginateCallbacks.hasLoadedAllItems())
    }

    @Test
    fun testOnItemClickListener() {
        val image = mockk<ImageModel>()
        val path = "image_path"
        val useCase = mockk<ListGalleryUseCase>()
        val pathUseCase = mockk<ImagePathUseCase>()
        coEvery { useCase.invoke(1, 20) } returns emptyList()
        every { pathUseCase.invoke(image) } returns path
        val viewModel = GalleryViewModel(useCase, pathUseCase, testDispatcher)

        viewModel.onItemClickListener.invoke(image)

        verify { pathUseCase.invoke(image) }
        assertEquals(path, viewModel.selectedPathItem.value)
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
