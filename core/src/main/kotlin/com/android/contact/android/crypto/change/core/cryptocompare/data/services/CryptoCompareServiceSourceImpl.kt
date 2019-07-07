package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.management.business.contact.internal.mapper.Mapper
import io.reactivex.Single

class CryptoCompareServiceSourceImpl(
    private val servce: CryptoCompareService,
    private val marketCapFullMapper: Mapper<MarketCapFullModel, MarketFullInfo>
) : CryptoCompareServiceSource {

    override fun getMarketFullInfo(limit: String, page: String, tsym: String): Single<MarketFullInfo> =
        servce.getMktCapFull(limit, page, tsym).map { marketCapFullMapper.map(it) }

}