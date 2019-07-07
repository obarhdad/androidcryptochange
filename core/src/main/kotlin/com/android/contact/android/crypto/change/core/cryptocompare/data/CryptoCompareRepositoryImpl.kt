package com.android.contact.android.crypto.change.core.cryptocompare.data

import com.android.contact.android.crypto.change.core.cryptocompare.data.databases.CryptoCompareDatabaseSource
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.CryptoCompareServiceSource
import com.android.contact.android.crypto.change.core.cryptocompare.domain.repositories.CryptoCompareRepository

class CryptoCompareRepositoryImpl(private val serviceSource: CryptoCompareServiceSource,
                                  private val databaseSource: CryptoCompareDatabaseSource) : CryptoCompareRepository {
}