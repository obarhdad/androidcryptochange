package com.android.contact.android.crypto.change.core.internal.extensions

import java.text.SimpleDateFormat
import java.util.*


val Long.toDate: String
    get() {
        val c = Calendar.getInstance()
        c.timeInMillis = this * 1000L
        val d = c.time
        val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm:ss")
        return sdf.format(d)
    }