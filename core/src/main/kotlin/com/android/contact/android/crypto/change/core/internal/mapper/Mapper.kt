package com.android.contact.management.business.contact.internal.mapper

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO

    fun from(to: TO): FROM = throw UnsupportedOperationException()
}
