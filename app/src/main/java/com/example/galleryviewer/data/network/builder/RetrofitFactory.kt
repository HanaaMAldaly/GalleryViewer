package com.example.galleryviewer.data.network.builder

import com.example.galleryviewer.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_RRL = "https://www.flickr.com/"

    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                it.proceed(
                    request.newBuilder().addHeader("accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .build(),
                )
            }

        Retrofit.Builder()
            .baseUrl(BASE_RRL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create(),
                ),
            )
            .client(client.build())
            .build()
    }

    fun getAPIClient(): Retrofit = retrofit
}
