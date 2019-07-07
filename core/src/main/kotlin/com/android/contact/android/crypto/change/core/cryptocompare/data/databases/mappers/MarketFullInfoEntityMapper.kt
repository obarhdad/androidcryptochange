package com.android.contact.android.crypto.change.core.cryptocompare.data.databases.mappers

import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models.MarketFullInfoEntity
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.management.business.contact.internal.mapper.Mapper

class MarketFullInfoEntityMapper : Mapper<MarketFullInfoEntity, MarketFullInfo> {
    override fun map(from: MarketFullInfoEntity): MarketFullInfo =
        MarketFullInfo(
            from.id,
            from.name,
            from.fullName,
            from.imageUrl
        )

    override fun from(to: MarketFullInfo): MarketFullInfoEntity =
        MarketFullInfoEntity(
            to.id,
            to.name,
            to.fullName,
            to.imageUrl
        )
}