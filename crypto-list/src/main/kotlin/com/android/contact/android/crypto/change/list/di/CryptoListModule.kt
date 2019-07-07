package com.android.contact.android.crypto.change.list.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.android.contact.android.crypto.change.list.CryptoListFragment
import com.android.contact.management.business.contact.internal.mapper.Mapper
import dagger.Module
import dagger.Provides

@Module
class CryptoListModule(private val fragment: CryptoListFragment) {
    @Provides
    fun provideContext(): Context = fragment.context!!
}