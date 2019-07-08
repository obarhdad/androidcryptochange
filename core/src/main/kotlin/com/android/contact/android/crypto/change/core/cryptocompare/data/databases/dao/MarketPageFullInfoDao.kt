package com.android.contact.android.crypto.change.core.cryptocompare.data.databases.dao

import androidx.room.*
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketFullInfoEntity
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketPageFullInfoEntity
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketPageWithFullInfoDaoEntity
import com.android.contact.android.crypto.change.core.internal.database.KDao
import io.reactivex.Maybe

@Dao
abstract class MarketPageFullInfoDao : KDao<MarketPageFullInfoEntity> {

    @Query("SELECT * FROM market_page_full_info,market_full_info WHERE market_page_full_info.page = :page AND market_full_info.page = market_page_full_info.page ")
    abstract fun getCryptoBy(page: Int): Maybe<MarketPageWithFullInfoDaoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMarketFullInfoEntity(fullInfoList: List<MarketFullInfoEntity>)

    @Transaction
    open fun insertMarketPageFullInfoEntity(
        fullInfo: List<MarketFullInfoEntity>,
        page: Int,
        tsym: String
    ) {
        insert(MarketPageFullInfoEntity(page, tsym))
        fullInfo.apply {
            forEach { item -> item.page = page }
            insertMarketFullInfoEntity(this)
        }
    }
}