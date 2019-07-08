package com.android.contact.android.crypto.change.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository

class CryptoListViewModelFactory(
    private val cryptoCompareRepository: CryptoCompareRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != CryptoListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CryptoListViewModel(cryptoCompareRepository) as T
    }
}