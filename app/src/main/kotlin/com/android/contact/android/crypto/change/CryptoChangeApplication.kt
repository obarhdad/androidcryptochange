package com.android.contact.android.crypto.change

import android.app.Application
import com.android.contact.android.crypto.change.core.internal.di.CoreComponent
import com.android.contact.android.crypto.change.core.internal.di.CoreComponentProvider
import com.android.contact.android.crypto.change.core.internal.di.DaggerCoreComponent

class CryptoChangeApplication : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent.builder()
                .context(this)
                .build()
        }
        return coreComponent
    }
}