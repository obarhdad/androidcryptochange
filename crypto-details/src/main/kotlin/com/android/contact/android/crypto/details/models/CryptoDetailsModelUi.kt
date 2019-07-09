package com.android.contact.android.crypto.details.models

data class CryptoDetailsModelUi(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val type: String,
    val market: String,
    val pairSymbol: String,
    val price: String,
    val dateTo: String,
    val dateFrom: String,
    val data: List<HistoricalHourlyDataModelUi>
)

data class HistoricalHourlyDataModelUi(
    val date: String,
    val close: String,
    val high: String,
    val low: String,
    val open: String,
    val volumefrom: String,
    val volumeto: String
)