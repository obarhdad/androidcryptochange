package com.android.contact.android.crypto.change.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.list.databinding.ViewCryptoItemBinding
import com.android.contact.android.crypto.change.list.ui.CryptoItemViewHolder

class CryptoListAdapter : PagedListAdapter<MarketFullInfo, CryptoItemViewHolder>(CryptoListDiff()) {
    lateinit var onItemClicked: ((String) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder =
        CryptoItemViewHolder(ViewCryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) =
        holder.bind(getItem(position)!!, onItemClicked)

    fun submit(
        onItemClicked: ((String) -> Unit),
        pagedList: PagedList<MarketFullInfo>
    ) {
        submitList(pagedList)
        this.onItemClicked = onItemClicked
    }
}

class CryptoListDiff : DiffUtil.ItemCallback<MarketFullInfo>() {
    override fun areItemsTheSame(oldItem: MarketFullInfo, newItem: MarketFullInfo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MarketFullInfo, newItem: MarketFullInfo): Boolean =
        oldItem == newItem
}