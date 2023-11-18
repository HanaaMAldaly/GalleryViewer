package com.example.galleryviewer.ui.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.usecase.ImagePathUseCase
import com.example.galleryviewer.domain.usecase.ListGalleryUseCase
import com.paginate.Paginate
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
    private var isPageLoading = false
    private var hasLoadedAllItems = false

    init {
        getImages(page, pageCount)
    }

    private fun getImages(page: Int, pageCount: Int) {
        isPageLoading = true
        viewModelScope.launch(exceptionHandler + dispatcher) {
            val items = useCase.invoke(page, pageCount)
            if (items.isEmpty()) hasLoadedAllItems = true
            val allItems = (_gallery.value ?: listOf()) + items
            _gallery.postValue(allItems)
            this@GalleryViewModel.page++
            isPageLoading = false
        }
    }

    val onItemClickListener: (ImageModel) -> Unit = {
        _selectedPathItem.value = pathUseCase.invoke(it)
    }

    fun removeSelectedItem() {
        _selectedPathItem.value = null
    }

    var paginateCallbacks: Paginate.Callbacks = object : Paginate.Callbacks {
        override fun onLoadMore() {
            getImages(page, pageCount)
        }

        override fun isLoading(): Boolean {
            // Indicate whether new page loading is in progress or not
            return isPageLoading
        }

        override fun hasLoadedAllItems(): Boolean {
            // Indicate whether all data (pages) are loaded or not
            return hasLoadedAllItems
        }
    }
}
