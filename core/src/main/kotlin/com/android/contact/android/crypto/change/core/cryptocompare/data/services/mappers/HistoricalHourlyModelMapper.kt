package com.android.contact.android.crypto.change.core.cryptocompare.data.services.mappers

import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.HistoricalHourlyDataModel
import com.android.contact.android.crypto.change.core.cryptocompare.data.services.models.HistoricalHourlyModel
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourly
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.HistoricalHourlyData
import com.android.contact.android.crypto.change.core.internal.extensions.toDate
import com.android.contact.android.crypto.change.core.internal.mapper.Mapper

class HistoricalHourlyModelMapper : Mapper<HistoricalHourlyModel, HistoricalHourly> {
    override fun map(from: HistoricalHourlyModel): HistoricalHourly =
        HistoricalHourly(
            from.timeTo.toDate,
            from.timeFrom.toDate,
            from.data.map(this::map)
        )

    private fun map(from: HistoricalHourlyDataModel): HistoricalHourlyData =
        HistoricalHourlyData(
            from.time.toDate,
            from.close,
            from.high,
            from.low,
            from.open,
            from.volumefrom,
            from.volumeto
        )
}