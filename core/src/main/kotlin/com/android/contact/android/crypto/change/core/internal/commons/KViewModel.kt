package com.android.contact.android.crypto.change.core.internal.commons

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class KViewModel : ViewModel() {
    protected val subscription: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

}