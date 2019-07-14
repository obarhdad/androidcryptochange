package com.android.contact.android.crypto.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases.GetCryptoDetailsUseCase
import com.android.contact.android.crypto.change.core.internal.commons.KSchedulers
import com.android.contact.android.crypto.change.core.internal.commons.KViewModel
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import com.android.contact.android.crypto.details.models.CryptoDetailsModelUi
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class CryptoDetailsViewModel(
    private val schedulers: KSchedulers,
    private val getCryptoDetails: GetCryptoDetailsUseCase,
    private val cryptoDetailsMapper: Mapper<Pair<MarketFullByPair, HistoricalHourly>, CryptoDetailsModelUi>
) : KViewModel() {

    private val _cryptoDetails: MutableLiveData<CryptoDetailsModelUi> by lazy {
        MutableLiveData<CryptoDetailsModelUi>()
    }

    val cryptoDetails: LiveData<CryptoDetailsModelUi>
        get() = _cryptoDetails

    fun getCryptoDetails(
        fsym: String,
        tsym: String
    ) {
        subscription.add(
            getCryptoDetails.execute(CRYPTO_DETAIL_LIMIT, fsym, tsym).toObservable()
                .repeatWhen { o -> o.concatMap { Observable.timer(CRYPTO_DETAIL_TIME, TimeUnit.MINUTES) } }
                .map(cryptoDetailsMapper::map)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    _cryptoDetails.value = it
                }, {
                    _errorLiveData.value = it
                })
        )
    }
    companion object {
        const val CRYPTO_DETAIL_LIMIT = 20
        const val CRYPTO_DETAIL_TIME = 2L

    }
}