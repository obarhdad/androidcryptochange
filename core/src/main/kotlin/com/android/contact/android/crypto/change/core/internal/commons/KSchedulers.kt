package com.android.contact.android.crypto.change.core.internal.commons

import io.reactivex.Scheduler

interface KSchedulers {
    fun io(): Scheduler

    fun main(): Scheduler
}