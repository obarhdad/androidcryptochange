package com.android.contact.android.crypto.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.contact.android.crypto.change.core.internal.communication.AppCommunication
import com.android.contact.android.crypto.change.core.internal.di.CoreInjectHelper
import com.android.contact.android.crypto.details.adapter.CryptoDetailsAdapter
import com.android.contact.android.crypto.details.databinding.FragmentCryptoDetailsBinding
import com.android.contact.android.crypto.details.di.CryptoDetailsModule
import com.android.contact.android.crypto.details.di.DaggerCryptoDetailsComponent
import com.android.contact.android.crypto.details.models.CryptoDetailsModelUi
import com.android.contact.android.crypto.details.viewmodel.CryptoDetailsViewModel
import javax.inject.Inject


class CryptoDetailsFragment : Fragment(),
    AppCommunication.Module {

    @Inject
    internal lateinit var cryptoDetailsViewModel: CryptoDetailsViewModel

    @Inject
    internal lateinit var navigation: AppCommunication.Navigation
    lateinit var binding: FragmentCryptoDetailsBinding

    private val tSym: String
        get() = arguments?.getString(ARG_TSYM)
            ?: throw IllegalStateException()

    private val fSym: String
        get() = arguments?.getString(ARG_FSYM)
            ?: throw IllegalStateException()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentCryptoDetailsBinding.inflate(inflater, container, false)
            .run {
                binding = this
                cryptoDetailsListRecyclerView.apply {
                    adapter = CryptoDetailsAdapter()
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
        DaggerCryptoDetailsComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(activity!!.applicationContext))
            .cryptoDetailsModule(CryptoDetailsModule(this))
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        cryptoDetailsViewModel.cryptoDetails.apply {
            if (value == null) cryptoDetailsViewModel.getCryptoDetails(fSym, tSym)
            observe(this@CryptoDetailsFragment, Observer(this@CryptoDetailsFragment::setCryptoDetails))
        }
        cryptoDetailsViewModel.error.apply {
            observe(this@CryptoDetailsFragment, Observer { navigation.onShowError() })
        }
    }

    private fun setCryptoDetails(crypto: CryptoDetailsModelUi) {
        binding.apply {
            this.crypto = crypto
            (cryptoDetailsListRecyclerView.adapter as CryptoDetailsAdapter).submitList(crypto.data)
            cryptoDetailsListRecyclerView.setHasFixedSize(false)
        }
    }

    companion object {
        private val TAG = CryptoDetailsFragment::class.java.simpleName
        private val ARG_FSYM = "$TAG.ARG_FSYM"
        private val ARG_TSYM = "$TAG.ARG_TSYM"

        fun newInstance(fSym: String, tSym: String) = CryptoDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_FSYM, fSym)
                putString(ARG_TSYM, tSym)

            }
        }
    }
}