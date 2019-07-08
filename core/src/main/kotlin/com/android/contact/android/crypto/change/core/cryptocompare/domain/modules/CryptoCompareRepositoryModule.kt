package com.android.contact.android.crypto.change.core.cryptocompare.domain.modules

import com.android.contact.android.crypto.change.core.cryptocompare.data.CryptoCompareRepositoryImpl
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.CryptoCompareDatabaseSource
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.CryptoCompareDatabaseSourceImpl
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.dao.MarketPageFullInfoDao
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.mappers.MarketFullInfoEntityMapper
import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketFullInfoEntity
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.CryptoCompareService
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.CryptoCompareServiceSource
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.CryptoCompareServiceSourceImpl
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.mappers.MarketCapFullModelMapper
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.internal.database.AppDatabase
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CryptoCompareRepositoryModule {
    @Provides
    fun provideCryptoCompareService(retrofit: Retrofit): CryptoCompareService =
        retrofit.create(CryptoCompareService::class.java)

    @Provides
    fun provideMarketCapFullModelMapper(): Mapper<MarketCapFullInfoModel, MarketFullInfo> =
        MarketCapFullModelMapper()

    @Provides
    fun provideCryptoCompareServiceSource(
        service: CryptoCompareService,
        marketCapFullMapper: Mapper<MarketCapFullInfoModel, MarketFullInfo>
    ): CryptoCompareServiceSource =
        CryptoCompareServiceSourceImpl(service, marketCapFullMapper)

    @Provides
    fun providemarketFullInfoDao(appDatabase: AppDatabase): MarketPageFullInfoDao =
        appDatabase.marketPageFullInfo()

    @Provides
    fun provideMarketFullInfoEntityMapper(): Mapper<MarketFullInfoEntity, MarketFullInfo> =
        MarketFullInfoEntityMapper()

    @Provides
    fun provideCryptoCompareDatabaseSource(
        marketFullInfoDao: MarketPageFullInfoDao,
        marketFullInfoMapper: Mapper<MarketFullInfoEntity, MarketFullInfo>
    ): CryptoCompareDatabaseSource =
        CryptoCompareDatabaseSourceImpl(marketFullInfoDao, marketFullInfoMapper)

    @Provides
    fun provideCryptoCompareRepository(
        serviceSource: CryptoCompareServiceSource,
        databaseSource: CryptoCompareDatabaseSource
    ): CryptoCompareRepository =
        CryptoCompareRepositoryImpl(serviceSource, databaseSource)
}