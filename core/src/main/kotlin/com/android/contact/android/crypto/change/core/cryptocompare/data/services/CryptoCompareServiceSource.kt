package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import io.reactivex.Single

interface CryptoCompareServiceSource {
    fun getMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>>
}