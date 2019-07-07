package com.android.contact.management.business.contact.internal.usecases

import io.reactivex.Single

interface IUseCaseParametrized<P, R> {

    fun execute(param: P): Single<R>
}