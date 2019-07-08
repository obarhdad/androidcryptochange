package com.android.contact.android.crypto.change.core.cryptocompare.domain.usecases

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetCryptoDetailsUseCase(
    private val getMarketFull: GetMarketFullByPairUseCase,
    private val getHistorical: GetHistoricalHourUseCase
) {

    fun execute(
        limit: Int,
        fsym: String,
        tsym: String
    ): Single<Pair<MarketFullByPair, HistoricalHourly>> =
        Single.zip(getMarketFull.execute(fsym, tsym),
            getHistorical.execute(limit, fsym, tsym),
            BiFunction { marketFull, historical -> marketFull to historical })

}