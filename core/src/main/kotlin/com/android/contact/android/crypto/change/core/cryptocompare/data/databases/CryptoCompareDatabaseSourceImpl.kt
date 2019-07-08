package com.android.contact.android.crypto.change.core.cryptocompare.data.databases

import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.dao.MarketPageFullInfoDao
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketFullInfoEntity
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import io.reactivex.Maybe

class CryptoCompareDatabaseSourceImpl(
    private val marketFullInfoDao: MarketPageFullInfoDao,
    private val marketFullInfoMapper: Mapper<MarketFullInfoEntity, MarketFullInfo>
) : CryptoCompareDatabaseSource {

    override fun saveMarketFullInfoList(page: Int, tsym: String, marketFullInfoList: List<MarketFullInfo>) {
        marketFullInfoDao.insertMarketPageFullInfoEntity(
            marketFullInfoList.map(marketFullInfoMapper::from),
            page,
            tsym
        )
    }

    override fun getMarketFullInfo(page: Int, tsym: String): Maybe<List<MarketFullInfo>> =
        marketFullInfoDao.getCryptoBy(page).map {
            it.fullInfo?.map(marketFullInfoMapper::map)
        }


}