package com.android.contact.android.crypto.change.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.contact.android.crypto.change.R
import com.android.contact.android.crypto.change.core.internal.communication.AppCommunication
import com.android.contact.android.crypto.change.core.internal.extensions.inTransaction
import com.android.contact.android.crypto.change.list.CryptoListFragment
import com.android.contact.android.crypto.details.CryptoDetailsFragment

class MainActivity : AppCompatActivity(),
    AppCommunication.Navigation {

    private lateinit var currentFragment: AppCommunication.Module

    override fun onShowCryptoDetails(fSym: String, tSym: String) =
        replaceFragment(CryptoDetailsFragment.newInstance(fSym, tSym))

    override fun onShowCryptoList(tSym: String) =
        replaceFragment(CryptoListFragment.newInstance(tSym))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            currentFragment = getCurrentFragment()
        } ?: run {
            onShowCryptoList(CURRENCY_SYMBOL)
        }
    }

    override fun onBackPressed() {
        if (!currentFragment.onBackPressed()) {
            super.onBackPressed()
        }
    }

    private fun getCurrentFragment(): AppCommunication.Module =
        supportFragmentManager
            .findFragmentById(R.id.container) as? AppCommunication.Module
            ?: throw IllegalStateException("No fragment found!")


    private fun <T> replaceFragment(fragment: T) where T : Fragment, T : AppCommunication.Module =
        supportFragmentManager.inTransaction {
            currentFragment = fragment
            replace(R.id.main_fragment, fragment)
            addToBackStack(fragment.tag)
        }

    companion object {
        const val CURRENCY_SYMBOL = "EUR"
    }
}