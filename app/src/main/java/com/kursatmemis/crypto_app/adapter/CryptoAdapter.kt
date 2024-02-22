package com.kursatmemis.crypto_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kursatmemis.crypto_app.R
import com.kursatmemis.crypto_app.databinding.CryptoItemBinding
import com.kursatmemis.crypto_app.model.Crypto
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CryptoAdapter @Inject constructor(
    @ActivityContext context: Context,
    private val cryptoList: ArrayList<Crypto>
) :
    ArrayAdapter<Crypto>(context, R.layout.crypto_item, cryptoList) {

    private lateinit var binding: CryptoItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            binding = CryptoItemBinding.inflate(layoutInflater, parent, false)
        } else {
            binding = CryptoItemBinding.bind(convertView)
        }

        val crypto = cryptoList[position]
        val currency = crypto.currency
        val price = crypto.price

        binding.currencyTextView.text = currency
        binding.priceTextView.text = price

        return binding.root
    }

    fun updateAdapter(newCryptoList: ArrayList<Crypto>) {
        clear()
        addAll(newCryptoList)
        notifyDataSetChanged()
    }

}