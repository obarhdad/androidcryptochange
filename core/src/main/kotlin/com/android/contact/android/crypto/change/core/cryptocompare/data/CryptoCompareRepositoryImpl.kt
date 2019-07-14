package com.android.contact.android.crypto.change.core.cryptocompare.data

import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.CryptoCompareDatabaseSource
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.CryptoCompareServiceSource
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import io.reactivex.Single

class CryptoCompareRepositoryImpl(
    private val serviceSource: CryptoCompareServiceSource,
    private val databaseSource: CryptoCompareDatabaseSource
) : CryptoCompareRepository {

    override fun getHistoricalHour(
        limit: Int,
        fsym: String,
        tsym: String
    ): Single<HistoricalHourly> =
        serviceSource.getHistoricalHour(limit, fsym, tsym)

    override fun getMarketFullByPair(
        fsym: String,
        tsym: String
    ): Single<MarketFullByPair> =
        serviceSource.getMarketFullByPair(fsym, tsym)

    override fun getMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>> =
            getAndSaveMarketFullInfo(limit, page, tsym).onErrorResumeNext {
                databaseSource.getMarketFullInfo(page, tsym).toSingle()
            }


    private fun getAndSaveMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>> =
        serviceSource.getMarketFullInfo(limit, page, tsym)
            .doOnSuccess {
                databaseSource.saveMarketFullInfoList(page, tsym, it)
            }

    override fun getPriceByPair(
        fsym: String,
        tsym: String
    ): Single<HashMap<String, String>> =
        serviceSource.getPriceByPair(fsym, tsym)
}