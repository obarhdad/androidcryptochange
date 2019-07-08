package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.HistoricalHourlyModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketFullByPairModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareService {

    @GET("data/top/mktcapfull")
    fun getMktCapFull(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("tsym") tsym: String
    ): Single<MarketCapFullModel>

    @GET("data/histohour")
    fun getHistoricalHour(
        @Query("limit") limit: Int,
        @Query("fsym") fsym: String,
        @Query("tsym") tsym: String
    ): Single<HistoricalHourlyModel>

    @GET("data/top/exchanges/full")
    fun getMarketFullByPair(
        @Query("fsym") fsym: String,
        @Query("tsym") tsym: String
    ): Single<MarketFullByPairModel>
}