package com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import io.reactivex.Single

interface CryptoCompareRepository{
    fun getMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>>
}