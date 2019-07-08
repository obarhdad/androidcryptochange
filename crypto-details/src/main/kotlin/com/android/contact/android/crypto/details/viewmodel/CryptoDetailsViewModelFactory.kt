package com.android.contact.android.crypto.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases.GetCryptoDetailsUseCase
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import com.android.contact.android.crypto.details.models.CryptoDetailsModelUi

class CryptoDetailsViewModelFactory(
    private val schedulers: KSchedulers,
    private val getGryptoDetails: GetCryptoDetailsUseCase,
    private val cryptoDetailsMapper: Mapper<Pair<MarketFullByPair, HistoricalHourly>, CryptoDetailsModelUi>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != CryptoDetailsViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CryptoDetailsViewModel(schedulers, getGryptoDetails, cryptoDetailsMapper) as T
    }
}