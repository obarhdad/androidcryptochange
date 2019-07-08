package com.android.contact.android.crypto.change.core.cryptocompare.data.services.models

import com.google.gson.annotations.SerializedName

data class MarketCapFullModel(
    @SerializedName("Data")
    val data: List<MarketCapFullInfoModel>)

data class MarketCapFullInfoModel(
    @SerializedName("CoinInfo")
    val coinInfo: MarketCapFullInfoCoinInfoModel,
    @SerializedName("DISPLAY")
    val display: HashMap<String, MarketCapFullInfoDisplayModel>
)

data class MarketCapFullInfoCoinInfoModel(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val name: String,
    @SerializedName("FullName") val fullName: String,
    @SerializedName("ImageUrl") val imageUrl: String
)

data class MarketCapFullInfoDisplayModel(
    @SerializedName("PRICE") val price: String,
    @SerializedName("FROMSYMBOL") val fromSymbol: String,
    @SerializedName("TOSYMBOL") val toSymbol: String
)
