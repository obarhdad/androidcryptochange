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

    lateinit var onCryptoPrice: ((String, String) -> Unit)

    private fun realProductIndex(id: String?) =
        id?.let { currentList?.indexOfFirst { id == it.id } }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder =
        CryptoItemViewHolder(ViewCryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) =
        holder.bind(getItem(position)!!, onItemClicked, onCryptoPrice)

    fun submit(
        onItemClicked: ((String) -> Unit),
        onCryptoPrice: ((String, String) -> Unit),
        pagedList: PagedList<MarketFullInfo>
    ) {
        submitList(pagedList)
        this.onItemClicked = onItemClicked
        this.onCryptoPrice = onCryptoPrice
    }

    fun submit(
        cryptoId: String,
        price: String
    ) {
        realProductIndex(cryptoId)?.apply {
            currentList?.get(this)?.price = price
        }?.also {
            notifyItemChanged(it)
        }
    }

}

class CryptoListDiff : DiffUtil.ItemCallback<MarketFullInfo>() {
    override fun areItemsTheSame(oldItem: MarketFullInfo, newItem: MarketFullInfo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MarketFullInfo, newItem: MarketFullInfo): Boolean =
        oldItem.price == newItem.price
}