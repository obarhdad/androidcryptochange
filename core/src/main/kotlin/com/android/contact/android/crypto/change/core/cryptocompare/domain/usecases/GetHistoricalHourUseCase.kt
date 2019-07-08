package com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository
import io.reactivex.Single

class GetHistoricalHourUseCase(private val repository: CryptoCompareRepository) {
    fun execute(
        limit: Int,
        fsym: String,
        tsym: String
    ): Single<HistoricalHourly> =
        repository.getHistoricalHour(limit, fsym, tsym)

}