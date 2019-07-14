package com.android.contact.android.crypto.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.android.contact.android.crypto.details.databinding.ViewCryptoDetailsItemBinding
import com.android.contact.android.crypto.details.models.HistoricalHourlyDataModelUi
import com.android.contact.android.crypto.details.ui.CryptoDetailsItemViewHolder

class CryptoDetailsAdapter : ListAdapter<HistoricalHourlyDataModelUi, CryptoDetailsItemViewHolder>(HistoryListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoDetailsItemViewHolder =
        CryptoDetailsItemViewHolder(
            ViewCryptoDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CryptoDetailsItemViewHolder, position: Int) =
        holder.bind(getItem(position)!!)
}

class HistoryListDiff : DiffUtil.ItemCallback<HistoricalHourlyDataModelUi>() {
    override fun areItemsTheSame(
        oldItem: HistoricalHourlyDataModelUi,
        newItem: HistoricalHourlyDataModelUi
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: HistoricalHourlyDataModelUi,
        newItem: HistoricalHourlyDataModelUi
    ): Boolean =
        oldItem == newItem
}