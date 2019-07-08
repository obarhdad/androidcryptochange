package com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models

import androidx.room.Embedded
import androidx.room.Relation

data class MarketPageWithFullInfoDaoEntity(
    @Embedded
    var page: MarketPageFullInfoEntity = MarketPageFullInfoEntity(0, ""),
    @Relation(parentColumn = "page", entityColumn = "page", entity = MarketFullInfoEntity::class)
    var fullInfo: List<MarketFullInfoEntity>? = mutableListOf()
)