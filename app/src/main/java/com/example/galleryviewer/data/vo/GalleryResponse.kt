package com.example.galleryviewer.data.vo

import com.google.gson.annotations.SerializedName

data class GalleryResponse(
    @SerializedName("photos") var photos: PhotosResponse?,
    @SerializedName("stat") var stat: String?

)