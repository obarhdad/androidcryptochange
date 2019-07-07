package com.android.contact.android.crypto.change.core.internal.di

import android.content.Context
import com.android.contact.android.crypto.change.core.internal.di.CoreComponent
import com.android.contact.android.crypto.change.core.internal.di.CoreComponentProvider

object CoreInjectHelper {
    fun provideCoreComponent(applicationContext: Context): CoreComponent {
        return if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException("The application context you have passed does not implement CoreComponentProvider")
        }
    }
}