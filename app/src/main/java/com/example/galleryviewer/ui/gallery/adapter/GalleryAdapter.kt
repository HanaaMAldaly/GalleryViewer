package com.example.galleryviewer.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.galleryviewer.databinding.ItemAdsBinding
import com.example.galleryviewer.databinding.ItemGalleryBinding
import com.example.galleryviewer.domain.model.ImageModel
import com.example.galleryviewer.domain.usecase.ImagePathUseCase
import com.example.galleryviewer.domain.usecase.LoadImageUseCase
import com.paginate.Paginate

class GalleryAdapter(
    private val items: ArrayList<ImageModel>,
    private val onItemClickListener: (ImageModel) -> Unit,
) :
    Adapter<ViewHolder>() {
    private val loadImageUseCase by lazy { LoadImageUseCase() }
    private val imagePathUseCase by lazy { ImagePathUseCase() }

    companion object {
        @JvmStatic
        @BindingAdapter(
            "app:bindGallery",
            "app:onItemClickListener",
            "app:paginateCallback",
        )
        fun bindGalley(
            recyclerView: RecyclerView,
            items: List<ImageModel>?,
            onItemClickListener: (ImageModel) -> Unit,
            paginateCallback: Paginate.Callbacks,
        ) {
            items?.let {
                recyclerView.adapter = GalleryAdapter(
                    items as ArrayList,
                    onItemClickListener,
                )
                Paginate.with(recyclerView, paginateCallback)
                    .setLoadingTriggerThreshold(2)
                    .addLoadingListItem(true)
                    .build()
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

    class AdsGalleyViewHolder(view: View) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == GalleryType.Ads.type) {
            val view = ItemAdsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
                .root
            return AdsGalleyViewHolder(view)
        }

        val binding = ItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int): Int {
        return (
            if (position % 5 == 0) {
                GalleryType.Ads
            } else {
                GalleryType.Image
            }
            ).type
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is GalleryViewHolder) {
            holder.onBind(items[position])
        }
    }
}

enum class GalleryType(val type: Int) {
    Image(0), Ads(1)
}
