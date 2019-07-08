package com.android.contact.android.crypto.details.mappers

import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourlyData
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper
import com.android.contact.android.crypto.details.models.CryptoDetailsModelUi
import com.android.contact.android.crypto.details.models.HistoricalHourlyDataModelUi

class CryptoDetailsModelUiMapper : Mapper<Pair<MarketFullByPair, HistoricalHourly>, CryptoDetailsModelUi> {
    override fun map(
        from: Pair<MarketFullByPair, HistoricalHourly>
    ): CryptoDetailsModelUi =
        CryptoDetailsModelUi(
            from.first.id,
            from.first.name,
            from.first.fullName,
            from.first.imageUrl,
            from.first.type,
            from.first.market,
            from.first.pairSymbol,
            from.first.price,
            from.second.dateTo,
            from.second.dateFrom,
            from.second.data.map(this::map)
        )

    private fun map(
        from: HistoricalHourlyData
    ): HistoricalHourlyDataModelUi =
        HistoricalHourlyDataModelUi(
            from.date,
            from.close,
            from.high,
            from.low,
            from.open,
            from.volumefrom,
            from.volumeto
        )
}