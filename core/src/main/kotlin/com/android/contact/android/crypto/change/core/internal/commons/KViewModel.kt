package com.android.contact.android.crypto.change.core.internal.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class KViewModel : ViewModel() {
    protected val subscription: CompositeDisposable = CompositeDisposable()


    protected val _errorLiveData: MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    val error: LiveData<Throwable>
        get() = _errorLiveData

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

}