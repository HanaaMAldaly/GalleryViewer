package com.example.galleryviewer.data.network.builder

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.verify
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryTest {
    @Test
    fun testRetrofitFactory() {
        val retrofit = mockk<Retrofit>()
        mockkConstructor(Retrofit.Builder::class)
        every { anyConstructed<Retrofit.Builder>().build() } returns retrofit

        RetrofitFactory.getAPIClient()

        verify {
            anyConstructed<Retrofit.Builder>().baseUrl(any<String>())
            anyConstructed<Retrofit.Builder>().addConverterFactory(any<GsonConverterFactory>())
            anyConstructed<Retrofit.Builder>().build()
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
