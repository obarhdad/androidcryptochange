package com.android.contact.android.crypto.change.core.cryptocompare.domain.models

data class MarketFullByPair(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val type: String,
    val market: String,
    val pairSymbol: String,
    val price: String
)