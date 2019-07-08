package com.android.contact.android.crypto.change.core.cryptocompare.data.databases.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "market_full_info",
    foreignKeys = [ForeignKey(
        entity = MarketPageFullInfoEntity::class,
        parentColumns = arrayOf("page"),
        childColumns = arrayOf("page"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MarketFullInfoEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val price: String,
    val symbol: String
) {
    @ColumnInfo(name = "page", index = true)
    var page: Int = 0
}