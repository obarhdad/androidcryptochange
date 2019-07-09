package com.android.contact.android.crypto.change.list.viewmodel

import androidx.paging.Config
import androidx.paging.toLiveData
import com.android.contact.android.crypto.change.core.cryptocompare.domain.pagination.CryptoCompareDataSourceFactory
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.internal.commons.KViewModel

class CryptoListViewModel(
    cryptoCompareRepository: CryptoCompareRepository,
    symCurrency: String
) : KViewModel() {

    val marketFullInfo = CryptoCompareDataSourceFactory(cryptoCompareRepository, symCurrency)
        .toLiveData(Config(pageSize = PAGE_SIZE, initialLoadSizeHint = INITIAL_LOADED_SIZE_HINT))

    fun refresh() = marketFullInfo.value?.dataSource?.invalidate()

    companion object {
        const val PAGE_SIZE = 20
        const val INITIAL_LOADED_SIZE_HINT = 30

    }
}

