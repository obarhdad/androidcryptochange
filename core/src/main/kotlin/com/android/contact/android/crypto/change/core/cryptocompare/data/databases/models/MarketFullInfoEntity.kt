package com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models

import androidx.room.Entity

@Entity(tableName = "market_full_info")
data class MarketFullInfoEntity(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String
)