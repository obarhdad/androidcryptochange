package com.android.contact.android.crypto.change.core.internal.commons

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersApp : KSchedulers {
    override fun main(): Scheduler =  AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()
}