package com.android.contact.android.crypto.change.core.cryptocompare.data.services.models

import com.google.gson.annotations.SerializedName

data class MarketCapFullModel(val data: MarketCapFullInfoModel)

data class MarketCapFullInfoModel(
    @SerializedName("CoinInfo")
    val coinInfo: MarketCapFullInfoCoinInfoModel
)

data class MarketCapFullInfoCoinInfoModel(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val name: String,
    @SerializedName("FullName") val fullName: String,
    @SerializedName("ImageUrl") val imageUrl: String
)
