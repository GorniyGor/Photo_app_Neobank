package com.recan.photo_app.presentation.base

interface PresenterFactory<V : PresentationView, P : BasePresenter<V>> {
    fun get(): P
}
