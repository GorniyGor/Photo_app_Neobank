package com.recan.photo_app.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : PresentationView> : BiddablePresenter<V> {
    protected val disposables = CompositeDisposable()

    private var weakRef = WeakReference<V>(null)
    protected val view: V? get() = weakRef.get()

    val isBinned: Boolean get() = view != null

    private var wasBinned: Boolean = false

    final override fun bindView(view: V) {
        weakRef = WeakReference(view)
        if (!wasBinned) {
            wasBinned = true
            onBindView(view)
        }
    }

    final override fun unbindView() {
        disposables.clear()
        weakRef.clear()
    }

    protected open fun onBindView(view: V) {}

    protected fun Disposable.clearAtTime() = disposables.add(this)
}