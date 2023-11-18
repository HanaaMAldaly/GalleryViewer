package com.example.galleryviewer.ui.gallery.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.galleryviewer.R
import com.example.galleryviewer.data.repository.GalleryRepositoryImpl
import com.example.galleryviewer.databinding.FragmentGalleryBinding
import com.example.galleryviewer.domain.usecase.ImagePathUseCase
import com.example.galleryviewer.domain.usecase.ListGalleryUseCase
import com.example.galleryviewer.ui.details.fragment.DetailsFragment.Companion.IMAGE_PATH_KEY
import com.example.galleryviewer.ui.gallery.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GalleryViewModel(
                    ListGalleryUseCase(GalleryRepositoryImpl()),
                    ImagePathUseCase(),
                ) as T
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
            .apply {
                viewModel = this@GalleryFragment.viewModel
                lifecycleOwner = this@GalleryFragment.viewLifecycleOwner

                viewModel?.selectedPathItem?.observe(viewLifecycleOwner) {
                    it?.let {
                        navigateToDetailsScreen(it)
                        viewModel?.removeSelectedItem()
                    }
                }
            }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    private fun navigateToDetailsScreen(path: String) {
        view?.let {
            val bundle = Bundle().apply {
                putString(IMAGE_PATH_KEY, path)
            }
            Navigation.findNavController(it)
                .navigate(R.id.galleryFragmentToGalleryDetailsAction, bundle)
        }
    }
}
