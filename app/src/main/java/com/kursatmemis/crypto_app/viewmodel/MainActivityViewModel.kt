package com.kursatmemis.crypto_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatmemis.crypto_app.config.CryptoAPIClient
import com.kursatmemis.crypto_app.model.Crypto
import com.kursatmemis.crypto_app.service.CryptoService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private var _cryptoList = MutableLiveData<ArrayList<Crypto>>()
    val cryptoList get() = _cryptoList
    private val retrofit = CryptoAPIClient.getClient()
    private val cryptoService = retrofit.create(CryptoService::class.java)

    fun fetchCryptoList() {
        viewModelScope.launch {
            val cryptoList = doNetworkCall()
            cryptoList?.let {
                this@MainActivityViewModel.cryptoList.value = it
            }
        }
    }

    private suspend fun doNetworkCall(): ArrayList<Crypto>? {
        return withContext(Dispatchers.IO) {
            val response = cryptoService.getCryptoList()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

}