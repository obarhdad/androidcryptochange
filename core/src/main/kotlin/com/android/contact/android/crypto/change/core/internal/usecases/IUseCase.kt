package com.android.contact.management.business.contact.internal.usecases

import io.reactivex.Single

interface IUseCase<T> {

    fun execute(): Single<T>
}