package com.example.galleryviewer.data.network.cloud

import com.example.galleryviewer.data.vo.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GalleryAPI {
    @GET("services/rest/method=flickr.photos.search&format=json&nojsoncallback=50&text=Color")
    suspend fun getGallery(
        @Query("page") page:Int,
        @Query("per_page")count:Int):GalleryResponse
}