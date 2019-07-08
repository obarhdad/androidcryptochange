package com.android.contact.android.crypto.change.core.internal.extensions

val String?.EmptyIfNull: String
    get() = this ?: ""

