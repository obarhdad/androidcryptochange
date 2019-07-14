package com.android.contact.android.crypto.change.core.cryptocompare.domain.models

data class MarketFullInfo(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    var price: String,
    val symbol: String
)