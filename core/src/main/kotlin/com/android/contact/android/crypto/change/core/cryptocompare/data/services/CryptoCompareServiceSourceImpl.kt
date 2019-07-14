package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.HistoricalHourlyModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketFullByPairModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import io.reactivex.Observable
import io.reactivex.Single

class CryptoCompareServiceSourceImpl(
    private val service: CryptoCompareService,
    private val marketCapFullMapper: Mapper<MarketCapFullInfoModel, MarketFullInfo>,
    private val historicalHourlyMapper: Mapper<HistoricalHourlyModel, HistoricalHourly>,
    private val marketFullByPairMapper: Mapper<MarketFullByPairModel, MarketFullByPair>
) : CryptoCompareServiceSource {
    override fun getPriceByPair(fsym: String, tsym: String): Single<HashMap<String, String>> =
        service.getPriceByPair(fsym, tsym)

    override fun getHistoricalHour(limit: Int, fsym: String, tsym: String): Single<HistoricalHourly> =
        service.getHistoricalHour(limit, fsym, tsym).map { historicalHourlyMapper.map(it) }

    override fun getMarketFullByPair(fsym: String, tsym: String): Single<MarketFullByPair> =
        service.getMarketFullByPair(fsym, tsym).map { marketFullByPairMapper.map(it) }

    override fun getMarketFullInfo(limit: Int, page: Int, tsym: String): Single<List<MarketFullInfo>> =
        service.getMktCapFull(limit, page, tsym).map { it.data.map(marketCapFullMapper::map) }

}