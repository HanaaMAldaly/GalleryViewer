package com.example.galleryviewer.ui.gallery.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galleryviewer.data.repository.GalleryRepositoryImpl
import com.example.galleryviewer.databinding.FragmentGalleryBinding
import com.example.galleryviewer.domain.usecase.ListGalleryUseCase
import com.example.galleryviewer.ui.gallery.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GalleryViewModel(
                    ListGalleryUseCase(GalleryRepositoryImpl()),
                ) as T
            }
        }
    })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentGalleryBinding.inflate(layoutInflater, container, false)
            .apply {
                viewModel = this@GalleryFragment.viewModel
                lifecycleOwner = this@GalleryFragment.viewLifecycleOwner
            }
            .root
    }
}
