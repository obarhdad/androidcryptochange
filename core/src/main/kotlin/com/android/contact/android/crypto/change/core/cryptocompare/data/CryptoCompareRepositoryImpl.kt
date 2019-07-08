package com.android.contact.android.crypto.change.core.cryptocompare.data

import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.CryptoCompareDatabaseSource
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.CryptoCompareServiceSource
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import io.reactivex.Single

class CryptoCompareRepositoryImpl(
    private val serviceSource: CryptoCompareServiceSource,
    private val databaseSource: CryptoCompareDatabaseSource
) : CryptoCompareRepository {

    override fun getMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>> =
        databaseSource.getMarketFullInfo(page, tsym).switchIfEmpty(
            getAndSaveMarketFullInfo(limit, page, tsym)
        )

    private fun getAndSaveMarketFullInfo(
        limit: Int,
        page: Int,
        tsym: String
    ): Single<List<MarketFullInfo>> =
        serviceSource.getMarketFullInfo(limit, page, tsym)
            .doOnSuccess {
                databaseSource.saveMarketFullInfoList(page, tsym, it)
            }
}