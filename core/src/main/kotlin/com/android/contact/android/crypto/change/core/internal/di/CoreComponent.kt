package com.android.contact.android.crypto.change.core.internal.di

import android.content.Context
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.database.AppDatabase
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): CoreComponent
    }

    fun getAppContext(context: Context): Context
    fun getSchedulers(): KSchedulers
    fun getRetrofit(): Retrofit
    fun getAppDatabase(): AppDatabase
}