package com.android.contact.android.crypto.change.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers

class CryptoListViewModelFactory(
    private val schedulers: KSchedulers,
    private val cryptoCompareRepository: CryptoCompareRepository,
    private val symCurrency: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != CryptoListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CryptoListViewModel(schedulers,cryptoCompareRepository, symCurrency) as T
    }
}