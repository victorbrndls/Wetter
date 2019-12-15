package com.harystolho.wetter.util

import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

open class DefaultSingleObserver<T> : SingleObserver<T> {
    override fun onSuccess(t: T) {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
    }

}
