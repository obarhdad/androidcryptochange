package com.android.contact.android.crypto.change.list.ui

import androidx.recyclerview.widget.RecyclerView
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.extensions.addRipple
import com.android.contact.android.crypto.change.list.databinding.ViewCryptoItemBinding

class CryptoItemViewHolder(
    private val binding: ViewCryptoItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MarketFullInfo) {
        binding.apply {
            marketFullInfo = item
            executePendingBindings()
            container.addRipple()
        }
    }
}