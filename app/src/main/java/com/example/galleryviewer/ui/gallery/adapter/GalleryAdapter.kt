package com.example.galleryviewer.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.galleryviewer.databinding.ItemGalleryBinding
import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.usecase.ImagePathUseCase
import com.example.galleryviewer.domain.usecase.LoadImageUseCase

class GalleryAdapter(
    private val items: ArrayList<ImageModel>,
    private val onItemClickListener: (ImageModel) -> Unit,
) :
    Adapter<GalleryAdapter.GalleryViewHolder>() {
    private val loadImageUseCase by lazy { LoadImageUseCase() }
    private val imagePathUseCase by lazy { ImagePathUseCase() }

    companion object {
        @JvmStatic
        @BindingAdapter("app:bindGallery", "app:onItemClickListener")
        fun bindGalley(
            recyclerView: RecyclerView,
            items: List<ImageModel>?,
            onItemClickListener: (ImageModel) -> Unit,
        ) {
            items?.let {
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = GalleryAdapter(
                        items as ArrayList,
                        onItemClickListener,
                    )
                } else {
                    (recyclerView.adapter as GalleryAdapter).addItems(items)
                }
            }
        }
    }

    private fun addItems(list: List<ImageModel>) {
        val oldSize = items.size
        items.addAll(list)
        notifyItemRangeChanged(oldSize, items.size)
    }

    inner class GalleryViewHolder(private val binding: ItemGalleryBinding) :
        ViewHolder(binding.root) {
        fun onBind(item: ImageModel) {
            binding.root.setOnClickListener {
                onItemClickListener.invoke(item)
            }
            loadImageUseCase(imagePathUseCase(item)) {
                binding.image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}
