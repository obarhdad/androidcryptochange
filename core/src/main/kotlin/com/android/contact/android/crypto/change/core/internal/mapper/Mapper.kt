package com.android.contact.android.crypto.change.core.internal.mapper

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO

    fun from(to: TO): FROM = throw UnsupportedOperationException()
}
