package com.android.contact.android.crypto.change.core.cryptocompare.data.databases

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import io.reactivex.Maybe

interface CryptoCompareDatabaseSource {
    fun getMarketFullInfo(
        page: Int,
        tsym: String
    ): Maybe<List<MarketFullInfo>>

    fun saveMarketFullInfoList(
        page: Int,
        tsym: String,
        marketFullInfoList: List<MarketFullInfo>
    )
}