package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import io.reactivex.Single

class CryptoCompareServiceSourceImpl(
    private val service: CryptoCompareService,
    private val marketCapFullMapper: Mapper<MarketCapFullInfoModel, MarketFullInfo>
) : CryptoCompareServiceSource {

    override fun getMarketFullInfo(limit: Int, page: Int, tsym: String): Single<List<MarketFullInfo>> =
        service.getMktCapFull(limit, page, tsym).map { it.data.map(marketCapFullMapper::map) }

}