package com.recan.photo_app.presentation.base

interface BiddablePresenter<V> {
    fun bindView(view: V)
    fun unbindView()
}