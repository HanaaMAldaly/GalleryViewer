package com.example.galleryviewer.ui.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.usecase.ImagePathUseCase
import com.example.galleryviewer.domain.usecase.ListGalleryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val useCase: ListGalleryUseCase,
    private val pathUseCase: ImagePathUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        // TODO Handle exceptions
    }
    private val _gallery = MutableLiveData<List<ImageModel>>()
    val gallery: LiveData<List<ImageModel>> = _gallery
    private val _selectedPathItem = MutableLiveData<String?>(null)
    val selectedPathItem: LiveData<String?> = _selectedPathItem
    private val pageCount = 20
    private var page = 1

    init {
        getImages(page, pageCount)
    }

    private fun getImages(page: Int, pageCount: Int) {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            _gallery.postValue(useCase.invoke(page, pageCount))
            this@GalleryViewModel.page++
        }
    }

    val onItemClickListener: (ImageModel) -> Unit = {
        _selectedPathItem.value = pathUseCase.invoke(it)
    }

    fun removeSelectedItem() {
        _selectedPathItem.value = null
    }
}
