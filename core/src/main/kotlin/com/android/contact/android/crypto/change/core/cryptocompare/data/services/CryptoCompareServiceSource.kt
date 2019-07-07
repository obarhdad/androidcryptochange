package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import io.reactivex.Single

interface CryptoCompareServiceSource {
    fun getMarketFullInfo(
        limit: String,
        page: String,
        tsym: String
    ): Single<MarketFullInfo>
}