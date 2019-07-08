package com.android.contact.android.crypto.change.list.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import com.android.contact.android.crypto.change.list.CryptoListFragment
import com.android.contact.android.crypto.change.list.viewmodel.CryptoListViewModel
import com.android.contact.android.crypto.change.list.viewmodel.CryptoListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CryptoListModule(private val fragment: CryptoListFragment) {
    @Provides
    fun provideContext(): Context = fragment.context!!

    @Provides
    fun provideCryptoListViewModelFactory(
        cryptoCompareRepository: CryptoCompareRepository
    ): CryptoListViewModelFactory =
        CryptoListViewModelFactory(cryptoCompareRepository)


    @Provides
    fun provideCryptoListViewModel(
        factory: CryptoListViewModelFactory
    ): CryptoListViewModel {
        return ViewModelProviders.of(fragment, factory)[CryptoListViewModel::class.java]
    }
}