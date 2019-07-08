package com.android.contact.android.crypto.change.core.cryptocompare.data.services.mappers

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketFullByPairModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketFullDataAggregatedByPairModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullByPair
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper

class MarketFullByPairModelMapper : Mapper<MarketFullByPairModel, MarketFullByPair> {
    override fun map(from: MarketFullByPairModel): MarketFullByPair =
        MarketFullByPair(
            from.data.coinInfo.id,
            from.data.coinInfo.name,
            from.data.coinInfo.fullName,
            URL_IMAGE.plus(from.data.coinInfo.imageUrl),
            from.data.aggregatedData.type,
            from.data.aggregatedData.market,
            getFromSymbol(from.data.aggregatedData).plus(getToSymbol(from.data.aggregatedData)),
            from.data.aggregatedData.price
        )

    private fun getFromSymbol(from: MarketFullDataAggregatedByPairModel): String =
        FROM.plus(from.fromSymbol)

    private fun getToSymbol(from: MarketFullDataAggregatedByPairModel): String =
        TO.plus(from.toSymbol)

    companion object {
        const val URL_IMAGE = "https://www.cryptocompare.com"
        const val FROM = "from "
        const val TO = " to "
    }
}