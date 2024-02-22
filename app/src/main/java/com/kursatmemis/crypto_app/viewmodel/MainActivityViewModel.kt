package com.kursatmemis.crypto_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatmemis.crypto_app.config.CryptoAPIClient
import com.kursatmemis.crypto_app.model.Crypto
import com.kursatmemis.crypto_app.service.CryptoService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private var _cryptoList = MutableLiveData<ArrayList<Crypto>>()
    val cryptoList get() = _cryptoList

    fun fetchCryptoList() {
        val retrofit = CryptoAPIClient.getClient()
        val cryptoService = retrofit.create(CryptoService::class.java)

        cryptoService.getCryptoList().enqueue(object : Callback<ArrayList<Crypto>>{
            override fun onResponse(call: Call<ArrayList<Crypto>>, response: Response<ArrayList<Crypto>>) {
                val cryptoList = response.body()
                cryptoList?.let {
                    _cryptoList.value = it
                }
            }

            override fun onFailure(call: Call<ArrayList<Crypto>>, t: Throwable) {
                Log.w("mKm - crypto", "onFailure: $t")
            }

        })
    }

}