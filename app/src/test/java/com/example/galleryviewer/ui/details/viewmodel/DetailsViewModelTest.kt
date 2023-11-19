package com.example.galleryviewer.ui.details.viewmodel

import android.widget.ImageView
import com.example.galleryviewer.domain.usecase.LoadImageUseCase
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.After
import org.junit.Test

class DetailsViewModelTest {
    @Test
    fun testLoadImage() {
        val path = "image_path"
        val imageCallback = { mockk<ImageView>() }
        val useCase = mockk<LoadImageUseCase>()
        every { useCase.invoke(path, imageCallback) } just runs
        val viewModel = DetailsViewModel(useCase)

        viewModel.loadImage(path, imageCallback)

        verify { useCase.invoke(path, imageCallback) }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
