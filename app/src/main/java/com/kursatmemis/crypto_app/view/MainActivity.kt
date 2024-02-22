package com.kursatmemis.crypto_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kursatmemis.crypto_app.adapter.CryptoAdapter
import com.kursatmemis.crypto_app.databinding.ActivityMainBinding
import com.kursatmemis.crypto_app.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cryptoAdapter: CryptoAdapter
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cryptoListView.adapter = cryptoAdapter
        mainActivityViewModel.fetchCryptoList()
        observeLiveData()

    }

    private fun observeLiveData() {
        mainActivityViewModel.cryptoList.observe(this) {
            Log.w("mKm - crypto", "Crpyto List: ${it.size}")
            val newCryptoList = it
            cryptoAdapter.updateAdapter(newCryptoList)
        }
    }

}