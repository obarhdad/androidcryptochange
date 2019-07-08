package com.android.contact.android.crypto.change.core.internal.extensions

import java.util.*

val Long.toDate: String
    get() = Date(this).toString()