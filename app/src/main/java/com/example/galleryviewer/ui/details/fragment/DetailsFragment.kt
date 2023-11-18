package com.example.galleryviewer.ui.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galleryviewer.databinding.FragmentDetailsBinding
import com.example.galleryviewer.domain.usecase.LoadImageUseCase
import com.example.galleryviewer.ui.details.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {
        const val IMAGE_PATH_KEY = "image_path_key"
    }

    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DetailsViewModel(LoadImageUseCase()) as T
                }
            }
        },
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentDetailsBinding.inflate(
            LayoutInflater.from(container?.context),
            container,
            false,
        ).apply {
            arguments?.getString(IMAGE_PATH_KEY)?.let {
                viewModel.loadImage(it) { detailsImage }
            }
        }
            .root
    }
}
