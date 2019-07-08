package com.android.contact.android.crypto.change.list.di

import com.android.contact.android.crypto.change.core.cryptocompare.domain.modules.CryptoCompareRepositoryModule
import com.android.contact.android.crypto.change.core.internal.di.CoreComponent
import com.android.contact.android.crypto.change.core.internal.di.FeatureScope
import com.android.contact.android.crypto.change.list.CryptoListFragment
import dagger.Component

@Component(
    modules = [CryptoListModule::class, CryptoCompareRepositoryModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface CryptoListComponent {
    fun inject(fragment: CryptoListFragment)
}