package com.android.contact.android.crypto.change.core.cryptocompare.domain.models

data class HistoricalHourly(
    val dateTo: String,
    val dateFrom: String,
    val data: List<HistoricalHourlyData>
)


data class HistoricalHourlyData(
    val date: String,
    val close: String,
    val high: String,
    val low: String,
    val open: String,
    val volumefrom: String,
    val volumeto: String
)