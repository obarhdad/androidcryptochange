package com.android.contact.android.crypto.change.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.android.contact.android.crypto.change.core.cryptocompare.domain.models.MarketFullInfo
import com.android.contact.android.crypto.change.core.internal.di.CoreInjectHelper
import com.android.contact.android.crypto.change.list.adapter.CryptoListAdapter
import com.android.contact.android.crypto.change.list.databinding.FragmentCryptoListBinding
import com.android.contact.android.crypto.change.list.di.CryptoListModule
import com.android.contact.android.crypto.change.list.di.DaggerCryptoListComponent
import com.android.contact.android.crypto.change.list.viewmodel.CryptoListViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.contact.android.crypto.change.core.internal.communication.AppCommunication


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
    }

    private fun setCriptoListAdapter(pagedList: PagedList<MarketFullInfo>) {
        (binding.cryptoListRecyclerView.adapter as CryptoListAdapter).submit(this::submitList, pagedList)
    }

    private fun submitList(fSym: String) {
        navigationApp.onShowCryptoDetails(fSym, tSym)
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