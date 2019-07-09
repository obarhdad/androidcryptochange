package com.android.contact.android.crypto.change.core.internal.extensions

import java.util.*

fun Calendar.addMinute(minute: Int): Long {
    add(Calendar.MINUTE, minute)
    return timeInMillis
}
