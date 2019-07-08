package com.android.contact.android.crypto.details.di

import com.android.contact.android.crypto.change.core.cryptocompare.domain.modules.CryptoCompareUseCasesModule
import com.android.contact.android.crypto.change.core.internal.di.CoreComponent
import com.android.contact.android.crypto.change.core.internal.di.FeatureScope
import com.android.contact.android.crypto.details.CryptoDetailsFragment
import dagger.Component

@Component(
    modules = [CryptoDetailsModule::class, CryptoCompareUseCasesModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface CryptoDetailsComponent {
    fun inject(fragment: CryptoDetailsFragment)
}