package com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import io.reactivex.Single

class GetMarketFullByPairUseCase(private val repository: CryptoCompareRepository) {
    fun execute(
        fsym: String,
        tsym: String
    ): Single<MarketFullByPair> =
        repository.getMarketFullByPair(fsym, tsym)
}