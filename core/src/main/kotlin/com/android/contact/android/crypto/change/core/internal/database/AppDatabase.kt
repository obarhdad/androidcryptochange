package com.android.contact.android.crypto.change.core.internal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.dao.MarketPageFullInfoDao
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketFullInfoEntity
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketPageFullInfoEntity

@Database(
    entities = [
        MarketPageFullInfoEntity::class,
        MarketFullInfoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marketPageFullInfo(): MarketPageFullInfoDao
}