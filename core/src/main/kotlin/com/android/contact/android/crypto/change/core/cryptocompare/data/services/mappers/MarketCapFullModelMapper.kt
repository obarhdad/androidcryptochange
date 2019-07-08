package com.android.contact.android.crypto.change.core.cryptocompare.data.services.mappers

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoCoinInfoModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoDisplayModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.extensions.EmptyIfNull
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper

class MarketCapFullModelMapper : Mapper<MarketCapFullInfoModel, MarketFullInfo> {
    override fun map(from: MarketCapFullInfoModel): MarketFullInfo =
        map(from.coinInfo, from.display)

    private fun map(
        from: MarketCapFullInfoCoinInfoModel,
        display: HashMap<String, MarketCapFullInfoDisplayModel>
    ): MarketFullInfo =
        MarketFullInfo(
            from.id,
            from.name,
            from.fullName,
            URL_IMAGE.plus(from.imageUrl),
            getPrice(display),
            getFromSymbol(display).plus(getToSymbol(display))
        )

    private fun getPrice(from: HashMap<String, MarketCapFullInfoDisplayModel>): String =
        from.keys.firstOrNull()?.let { from[it]?.price }.EmptyIfNull

    private fun getFromSymbol(from: HashMap<String, MarketCapFullInfoDisplayModel>): String =
        from.keys.firstOrNull()?.let { FROM.plus(from[it]?.fromSymbol) }.EmptyIfNull

    private fun getToSymbol(from: HashMap<String, MarketCapFullInfoDisplayModel>): String =
        from.keys.firstOrNull()?.let { TO.plus(from[it]?.toSymbol) }.EmptyIfNull

    companion object {
        const val URL_IMAGE = "https://www.cryptocompare.com"
        const val FROM = "from "
        const val TO = " to "
    }
}