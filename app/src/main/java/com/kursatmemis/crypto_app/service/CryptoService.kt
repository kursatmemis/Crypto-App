package com.kursatmemis.crypto_app.service

import com.kursatmemis.crypto_app.model.Crypto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CryptoService {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getCryptoList() : Response<ArrayList<Crypto>>

}