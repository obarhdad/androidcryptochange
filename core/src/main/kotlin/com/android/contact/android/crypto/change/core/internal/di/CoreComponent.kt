package com.android.contact.android.crypto.change.core.internal.di

import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun getSchedulers(): KSchedulers
}