package com.android.contact.android.crypto.change.core.cryptocompare.domain.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import io.reactivex.disposables.CompositeDisposable

class CryptoCompareDataSourceFactory(
    private val cryptoCompareRepository: CryptoCompareRepository,
    private val symCurrency: String
) : DataSource.Factory<Int, MarketFullInfo>() {

    val source: MutableLiveData<CryptoCompareDataSource> by lazy {
        MutableLiveData<CryptoCompareDataSource>()
    }

    override fun create(): DataSource<Int, MarketFullInfo> {
        val movieDataSource = CryptoCompareDataSource(CompositeDisposable(), cryptoCompareRepository, symCurrency)
        source.postValue(movieDataSource)
        return movieDataSource
    }
}