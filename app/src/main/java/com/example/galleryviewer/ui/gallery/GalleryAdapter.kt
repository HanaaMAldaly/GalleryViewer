package com.example.galleryviewer.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.galleryviewer.databinding.ItemGalleryBinding
import com.example.galleryviewer.domain.model.ImageModel

class GalleryAdapter(private val items: ArrayList<ImageModel>) :
    Adapter<GalleryAdapter.GalleryViewHolder>() {
    companion object {
        @JvmStatic
        @BindingAdapter("app:bindGallery")
        fun bindGalley(recyclerView: RecyclerView, items: List<ImageModel>?) {
            items?.let {
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = GalleryAdapter(items as ArrayList)
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

    class GalleryViewHolder(private val binding: ItemGalleryBinding) : ViewHolder(binding.root) {
        fun onBind(item: ImageModel) {
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
