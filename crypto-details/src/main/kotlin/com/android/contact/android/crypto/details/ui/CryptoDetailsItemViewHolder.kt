package com.android.contact.android.crypto.details.ui

import androidx.recyclerview.widget.RecyclerView
import com.android.contact.android.crypto.details.databinding.ViewCryptoDetailsItemBinding
import com.android.contact.android.crypto.details.models.HistoricalHourlyDataModelUi

class CryptoDetailsItemViewHolder(
    private val binding: ViewCryptoDetailsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: HistoricalHourlyDataModelUi
    ) {
        binding.apply {
            hystory = item
            executePendingBindings()
        }
    }


}