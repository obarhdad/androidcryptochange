package com.android.contact.android.crypto.change.core.cryptocompare.domain.pagination

import androidx.paging.PageKeyedDataSource
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import io.reactivex.disposables.CompositeDisposable

class CryptoCompareDataSource(
    private val subscription: CompositeDisposable,
    private val cryptoCompareRepository: CryptoCompareRepository
) : PageKeyedDataSource<Int, MarketFullInfo>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MarketFullInfo>) {
        cryptoCompareRepository.getMarketFullInfo(PAGE_PER_COUNT, FIRST_PAGE, SYM_CURRENCY)
            .let {
                subscription.add(it.subscribe({ data ->
                    callback.onResult(data, 0, PAGE_PER_COUNT, null, FIRST_PAGE + 1)
                }, {
                    // TODO
                }))
            }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MarketFullInfo>) {
        cryptoCompareRepository.getMarketFullInfo(PAGE_PER_COUNT, params.key, SYM_CURRENCY)
            .let {
                subscription.add(it.subscribe({ data ->
                    callback.onResult(data, params.key + 1)
                }, {
                    // TODO
                }))
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MarketFullInfo>) {
        cryptoCompareRepository.getMarketFullInfo(PAGE_PER_COUNT, params.key, SYM_CURRENCY)
            .let {
                subscription.add(it.subscribe({ data ->
                    callback.onResult(data, getPrevious(params))
                }, {
                    // TODO
                }))
            }
    }

    private fun getPrevious(params: LoadParams<Int>): Int? =
        if (params.key > 1) params.key - 1 else null

    companion object {
        const val FIRST_PAGE = 0
        const val PAGE_PER_COUNT = 20
        const val SYM_CURRENCY = "EUR"
    }
}