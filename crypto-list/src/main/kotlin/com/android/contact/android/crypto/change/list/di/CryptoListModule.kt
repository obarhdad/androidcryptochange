package com.android.contact.android.crypto.change.list.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.core.internal.communication.AppCommunication
import com.android.contact.android.crypto.change.list.CryptoListFragment
import com.android.contact.android.crypto.change.list.viewmodel.CryptoListViewModel
import com.android.contact.android.crypto.change.list.viewmodel.CryptoListViewModelFactory
import dagger.Module
import dagger.Provides

private typealias INavigationHandlePlus = AppCommunication.Navigation

@Module
class CryptoListModule(
    private val fragment: CryptoListFragment,
    private val symCurrency: String
) {
    @Provides
    fun provideContext(): Context = fragment.context!!

    @Provides
    fun provideCryptoListViewModelFactory(
        cryptoCompareRepository: CryptoCompareRepository
    ): CryptoListViewModelFactory =
        CryptoListViewModelFactory(cryptoCompareRepository, symCurrency)


    @Provides
    fun provideCryptoListViewModel(
        factory: CryptoListViewModelFactory
    ): CryptoListViewModel {
        return ViewModelProviders.of(fragment, factory)[CryptoListViewModel::class.java]
    }

    @Provides
    fun provideNavigationHandlePlus(): INavigationHandlePlus {
        return fragment.activity as INavigationHandlePlus
    }
}