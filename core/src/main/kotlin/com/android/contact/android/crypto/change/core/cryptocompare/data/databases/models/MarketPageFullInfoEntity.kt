package com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "market_page_full_info")
data class MarketPageFullInfoEntity(
    @PrimaryKey
    val page: Int,
    val tsym: String
)

@ColumnInfo(name = "created_at", index = true)
var createdAt: Long = Calendar.getInstance().timeInMillis