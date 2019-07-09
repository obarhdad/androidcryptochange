package com.android.contact.android.crypto.change.core.internal.communication

interface AppCommunication {
    interface Navigation {
        fun onShowCryptoDetails(fSym: String, tSym: String)
        fun onShowCryptoList(tSym: String)
        fun onShowError()
    }

    interface Module {
        fun onBackPressed(): Boolean = false
    }
}