package com.android.contact.android.crypto.change.core.internal.commons

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable

abstract class KPageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    protected val subscription: CompositeDisposable = CompositeDisposable()

    override fun invalidate() {
        super.invalidate()
        subscription.clear()
    }

}