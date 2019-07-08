package com.android.contact.android.crypto.change.core.cryptocompare.data.services.models

import com.google.gson.annotations.SerializedName

data class MarketFullByPairModel(
    @SerializedName("Data") val data: MarketFullDataByPairModel
)

data class MarketFullDataByPairModel(
    @SerializedName("CoinInfo") val coinInfo: MarketFullDataCoinInfoByPairModel,
    @SerializedName("AggregatedData") val aggregatedData: MarketFullDataAggregatedByPairModel
)

data class MarketFullDataCoinInfoByPairModel(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val name: String,
    @SerializedName("FullName") val fullName: String,
    @SerializedName("ImageUrl") val imageUrl: String
)

data class MarketFullDataAggregatedByPairModel(
    @SerializedName("TYPE") val type: String,
    @SerializedName("MARKET") val market: String,
    @SerializedName("FROMSYMBOL") val fromSymbol: String,
    @SerializedName("TOSYMBOL") val toSymbol: String,
    @SerializedName("PRICE") val price: String
)

