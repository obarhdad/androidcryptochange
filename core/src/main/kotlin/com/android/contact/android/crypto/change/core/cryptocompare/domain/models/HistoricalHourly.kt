package com.android.contact.android.crypto.change.core.cryptocompare.domain.models

data class HistoricalHourly(
    val dateTo: String,
    val dateFrom: String,
    val data: List<HistoricalHourlyData>
)


data class HistoricalHourlyData(
    val date: String,
    val close: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val volumefrom: Double,
    val volumeto: Double
)