package com.android.contact.android.crypto.details.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases.GetCryptoDetailsUseCase
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.communication.AppCommunication
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import com.android.contact.android.crypto.details.CryptoDetailsFragment
import com.android.contact.android.crypto.details.mappers.CryptoDetailsModelUiMapper
import com.android.contact.android.crypto.details.models.CryptoDetailsModelUi
import com.android.contact.android.crypto.details.viewmodel.CryptoDetailsViewModel
import com.android.contact.android.crypto.details.viewmodel.CryptoDetailsViewModelFactory
import dagger.Module
import dagger.Provides

private typealias INavigationHandlePlus = AppCommunication.Navigation

@Module
class CryptoDetailsModule(private val fragment: CryptoDetailsFragment) {
    @Provides
    fun provideContext(): Context = fragment.context!!

    @Provides
    fun provideCryptoListViewModelFactory(
        schedulers: KSchedulers,
        getGryptoDetails: GetCryptoDetailsUseCase,
        cryptoDetailsMapper: Mapper<Pair<MarketFullByPair, HistoricalHourly>, CryptoDetailsModelUi>
    ): CryptoDetailsViewModelFactory =
        CryptoDetailsViewModelFactory(schedulers, getGryptoDetails, cryptoDetailsMapper)


    @Provides
    fun provideCryptoDetailsViewModel(
        factory: CryptoDetailsViewModelFactory
    ): CryptoDetailsViewModel =
        ViewModelProviders.of(fragment, factory)[CryptoDetailsViewModel::class.java]

    @Provides
    fun provideCryptoDetailsMapper(): Mapper<Pair<MarketFullByPair, HistoricalHourly>, CryptoDetailsModelUi> =
        CryptoDetailsModelUiMapper()

    @Provides
    fun provideNavigationHandlePlus(): INavigationHandlePlus {
        return fragment.activity as INavigationHandlePlus
    }
}