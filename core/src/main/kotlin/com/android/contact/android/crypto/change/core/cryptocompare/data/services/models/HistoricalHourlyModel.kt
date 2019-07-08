package com.android.contact.android.crypto.change.core.cryptocompare.data.services.models

import com.google.gson.annotations.SerializedName

data class HistoricalHourlyModel(
    @SerializedName("TimeTo") val timeTo: Long,
    @SerializedName("TimeFrom") val timeFrom: Long,
    @SerializedName("Data") val data: List<HistoricalHourlyDataModel>
)


data class HistoricalHourlyDataModel(
    val time: Long,
    val close: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val volumefrom: Double,
    val volumeto: Double
)