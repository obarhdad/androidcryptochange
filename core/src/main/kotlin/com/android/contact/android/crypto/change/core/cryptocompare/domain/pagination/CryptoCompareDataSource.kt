package com.android.contact.android.crypto.change.core.cryptocompare.domain.pagination

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.internal.commons.KPageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CryptoCompareDataSource(
    private val cryptoCompareRepository: CryptoCompareRepository,
    private val symCurrency: String
) : KPageKeyedDataSource<Int, MarketFullInfo>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MarketFullInfo>) {
        subscription.add(
            cryptoCompareRepository.getMarketFullInfo(PAGE_PER_COUNT, FIRST_PAGE, symCurrency)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    callback.onResult(data, null, FIRST_PAGE + 1)
                }, {
                    // TODO
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MarketFullInfo>) {
        subscription.add(
            cryptoCompareRepository.getMarketFullInfo(PAGE_PER_COUNT, params.key, symCurrency)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    callback.onResult(data, params.key + 1)
                }, {
                    // TODO
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MarketFullInfo>) {
        subscription.add(
            cryptoCompareRepository.getMarketFullInfo(PAGE_PER_COUNT, params.key, symCurrency)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    callback.onResult(data, getPrevious(params))
                }, {
                    // TODO
                })
        )
    }

    private fun getPrevious(params: LoadParams<Int>): Int? =
        if (params.key > 1) params.key - 1 else null

    companion object {
        const val FIRST_PAGE = 0
        const val PAGE_PER_COUNT = 20
    }
}