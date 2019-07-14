package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import io.reactivex.Single

interface CryptoCompareServiceSource {
    fun getMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>>

    fun getHistoricalHour(
        limit: Int,
        fsym: String,
        tsym: String
    ): Single<HistoricalHourly>

    fun getMarketFullByPair(
        fsym: String,
        tsym: String
    ): Single<MarketFullByPair>

    fun getPriceByPair(
        fsym: String,
        tsym: String
    ): Single<HashMap<String, String>>
}