package com.example.galleryviewer.ui.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.repository.GalleryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        // TODO Handle exceptions
    }
    private val _gallery = MutableLiveData<List<ImageModel>>()
    val gallery: LiveData<List<ImageModel>> = _gallery
    private val pageCount = 20
    private var page = 1

    init {
        getImages(page, pageCount)
    }

    private fun getImages(page: Int, pageCount: Int) {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            _gallery.postValue(repository.getGallery(page, pageCount))
            this@GalleryViewModel.page++
        }
    }
}
