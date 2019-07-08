package com.android.contact.android.crypto.change.core.internal.di

import android.content.Context
import androidx.room.Room
import com.android.contact.android.crypto.change.core.BuildConfig
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.commons.SchedulersApp
import com.android.contact.android.crypto.change.core.internal.database.AppDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class CoreModule {
    @Provides
    fun provideSchedulers(): KSchedulers = SchedulersApp()

    @Provides
    fun provideRoom(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })

    @Provides
    fun provideRetrofit(okHttpBuilder: OkHttpClient.Builder): Retrofit =
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    companion object {
        const val ENDPOINT = "https://min-api.cryptocompare.com/"
        const val DATABASE_NAME = "crypto_compare.db"

    }
}