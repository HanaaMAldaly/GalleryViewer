package com.example.galleryviewer.data.network.cloud

import com.example.galleryviewer.BuildConfig
import com.example.galleryviewer.data.vo.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryAPI {
    @GET("services/rest")
    suspend fun getGallery(
        @Query("page") page: Int,
        @Query("per_page")count: Int,
        @Query("method") method: String = "flickr.photos.search",
        @Query("format")format: String = "json",
        @Query("nojsoncallback")nojsoncallback: Int = 50,
        @Query("text")text: String = "Color",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): GalleryResponse
}
