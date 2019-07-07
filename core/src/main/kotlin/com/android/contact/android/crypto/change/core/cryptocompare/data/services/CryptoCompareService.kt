package com.android.contact.android.crypto.change.core.cryptocompare.data.services

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.MarketCapFullModel
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoCompareService {

    @GET("data/top/mktcapfull")
    fun getMktCapFull(limit: String,
                      page: String,
                      tsym:String): Single<MarketCapFullModel>
}