package com.example.galleryviewer.ui.details.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.galleryviewer.domain.usecase.LoadImageUseCase

class DetailsViewModel(private val loadImageUseCase: LoadImageUseCase) : ViewModel() {
    fun loadImage(path: String, imageCallback: () -> ImageView) {
        loadImageUseCase.invoke(path, imageCallback)
    }
}
