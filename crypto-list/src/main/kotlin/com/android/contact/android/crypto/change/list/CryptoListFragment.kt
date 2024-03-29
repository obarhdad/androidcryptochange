package com.android.contact.android.crypto.change.list

import android.content.Context
import android.database.DataSetObserver
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.communication.AppCommunication
import com.android.contact.android.crypto.change.core.internal.di.CoreInjectHelper
import com.android.contact.android.crypto.change.list.adapter.CryptoListAdapter
import com.android.contact.android.crypto.change.list.databinding.FragmentCryptoListBinding
import com.android.contact.android.crypto.change.list.di.CryptoListModule
import com.android.contact.android.crypto.change.list.di.DaggerCryptoListComponent
import com.android.contact.android.crypto.change.list.viewmodel.CryptoListViewModel
import javax.inject.Inject


class CryptoListFragment : Fragment(),
    AppCommunication.Module {

    @Inject
    internal lateinit var cryptoListViewModel: CryptoListViewModel

    @Inject
    internal lateinit var navigationApp: AppCommunication.Navigation

    lateinit var binding: FragmentCryptoListBinding

    private val tSym: String
        get() = arguments?.getString(ARG_TSYM)
            ?: throw IllegalStateException()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentCryptoListBinding.inflate(inflater, container, false)
            .run {
                binding = this
                cryptoListRefreshLayout.isRefreshing = false
                cryptoListRecyclerView.apply {
                    adapter = CryptoListAdapter()
                    DividerItemDecoration(
                        context,
                        (layoutManager as LinearLayoutManager).orientation
                    ).also {
                        addItemDecoration(it)
                    }
                }
                return@run root
            }

    override fun onAttach(context: Context) {
        DaggerCryptoListComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(activity!!.applicationContext))
            .cryptoListModule(CryptoListModule(this, tSym))
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        cryptoListViewModel.marketFullInfo.observe(this, Observer(this::setCriptoListAdapter))
        cryptoListViewModel.cryptoPrice.observe(this@CryptoListFragment, Observer(this@CryptoListFragment::submitPrice))
    }

    private fun setCriptoListAdapter(pagedList: PagedList<MarketFullInfo>) {
        binding.apply {
            cryptoListRefreshLayout.isRefreshing = false
            (cryptoListRecyclerView.adapter as CryptoListAdapter).submit(
                this@CryptoListFragment::submitList,
                this@CryptoListFragment::getCryptoDetails,
                pagedList
            )
            cryptoListRefreshLayout.setOnRefreshListener { getInitialData() }
        }
    }

    private fun submitList(fSym: String) {
        navigationApp.onShowCryptoDetails(fSym, tSym)
    }


    private fun getInitialData() {
        binding.cryptoListRefreshLayout.isRefreshing = true
        cryptoListViewModel.refresh()
    }

    private fun getCryptoDetails(
        cryptoId: String,
        symbol: String
    ) {
        Log.d("Symbol", cryptoId)
        cryptoListViewModel.getCryptoDetails(symbol, cryptoId)
    }

    private fun submitPrice(map: Pair<String, String?>) {
        Log.d("Symbol", map.first)
        map.second?.let {
            (binding.cryptoListRecyclerView.adapter as CryptoListAdapter).submit(map.first, it)
        }
    }

    companion object {
        private val TAG = CryptoListFragment::class.java.simpleName
        private val ARG_TSYM = "$TAG.ARG_TSYM"

        fun newInstance(tSym: String) = CryptoListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TSYM, tSym)

            }
        }
    }
}