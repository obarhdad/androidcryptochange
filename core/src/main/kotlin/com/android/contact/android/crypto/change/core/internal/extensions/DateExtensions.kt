package com.android.contact.android.crypto.change.core.internal.extensions

import android.os.Handler
import java.util.*

fun Calendar.addMinute(minute: Int): Long {
    add(Calendar.MINUTE, minute)
    return timeInMillis
}

fun withDelay(delay : Long, block : () -> Unit) {
    Handler().postDelayed(Runnable(block), delay)
}