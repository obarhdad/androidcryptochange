package com.android.contact.android.crypto.change.core.internal.di

import android.content.Context
import androidx.room.Room
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.commons.SchedulersApp
import com.android.contact.android.crypto.change.core.internal.database.AppDatabase
import dagger.Module
import dagger.Provides


@Module
class CoreModule {
    @Provides
    fun provideSchedulers(): KSchedulers = SchedulersApp()

    @Provides
    fun provideRoom(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "crypto_compare.db").build()
}