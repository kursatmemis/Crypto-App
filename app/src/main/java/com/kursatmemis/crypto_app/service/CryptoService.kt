package com.kursatmemis.crypto_app.service

import com.kursatmemis.crypto_app.model.Crypto
import retrofit2.Call
import retrofit2.http.GET

interface CryptoService {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getCryptoList() : Call<ArrayList<Crypto>>

}