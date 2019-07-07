package com.android.contact.android.crypto.change.core.cryptocompare.data.services.mappers

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullInfoCoinInfoModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.management.business.contact.internal.mapper.Mapper

class MarketCapFullModelMapper : Mapper<MarketCapFullModel, MarketFullInfo> {
    override fun map(from: MarketCapFullModel): MarketFullInfo =
        map(from.data.coinInfo)

    private fun map(from: MarketCapFullInfoCoinInfoModel): MarketFullInfo =
        MarketFullInfo(
            from.id,
            from.name,
            from.fullName,
            from.imageUrl
        )
}