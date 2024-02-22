package com.kursatmemis.crypto_app.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoAPIClient {

    val BASE_URL = "https://raw.githubusercontent.com/"
    private var retrofit: Retrofit? = null

    fun getClient() : Retrofit{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit as Retrofit
    }

}