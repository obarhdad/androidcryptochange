package com.android.contact.android.crypto.change.core.cryptocompare.domain.modules

import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases.GetCryptoDetailsUseCase
import com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases.GetHistoricalHourUseCase
import com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases.GetMarketFullByPairUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [CryptoCompareRepositoryModule::class])
class CryptoCompareUseCasesModule {
    @Provides
    fun provideGetHistoricalHourUseCase(repository: CryptoCompareRepository): GetHistoricalHourUseCase =
        GetHistoricalHourUseCase(repository)

    @Provides
    fun provideGetMarketFullByPairUseCase(repository: CryptoCompareRepository): GetMarketFullByPairUseCase =
        GetMarketFullByPairUseCase(repository)

    @Provides
    fun provideGetGryptoDetailsUseCase(
        getMarketFull: GetMarketFullByPairUseCase,
        getHistorical: GetHistoricalHourUseCase
    ): GetCryptoDetailsUseCase =
        GetCryptoDetailsUseCase(getMarketFull, getHistorical)
}