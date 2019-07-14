package com.android.contact.android.crypto.change.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.toLiveData
import com.android.contact.android.crypto.change.core.cryptocompare.domain.pagination.CryptoCompareDataSourceFactory
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.commons.KViewModel

class CryptoListViewModel(
    private val schedulers: KSchedulers,
    private val cryptoCompareRepository: CryptoCompareRepository,
    private val symCurrency: String
) : KViewModel() {

    val marketFullInfo = CryptoCompareDataSourceFactory(cryptoCompareRepository, symCurrency)
        .toLiveData(
            Config(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            )
        )

    private val _cryptoPrice: MutableLiveData<Pair<String, String?>> by lazy {
        MutableLiveData<Pair<String, String?>>()
    }

    val cryptoPrice: LiveData<Pair<String, String?>>
        get() = _cryptoPrice

    fun refresh() = marketFullInfo.value?.dataSource?.invalidate()

    fun getCryptoDetails(
        symbol: String,
        cryptoId: String
    ) {
        subscription.add(
                cryptoCompareRepository.getPriceByPair(symbol, symCurrency)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    _cryptoPrice.value = cryptoId to it[symCurrency]
                }, {
                    _errorLiveData.value = it
                })
        )
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}

