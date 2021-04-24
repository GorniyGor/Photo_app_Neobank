package com.recan.photo_app.presentation.action_selection.di

import com.recan.photo_app.di.ActivityScope
import com.recan.photo_app.di.CommonModule
import com.recan.photo_app.presentation.action_selection.ActionSelectionContract
import com.recan.photo_app.presentation.action_selection.ActionSelectionPresenter
import com.recan.photo_app.presentation.action_selection.ActionSelectionRouter
import com.recan.photo_app.presentation.base.PresenterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [CommonModule::class])
interface ActionSelectionActivityModule {

    companion object {

        @ActivityScope
        @Provides
        fun getPresenterFactory(presenter: ActionSelectionContract.Presenter) =
            object :
                PresenterFactory<ActionSelectionContract.View, ActionSelectionContract.Presenter> {
                override fun get(): ActionSelectionContract.Presenter = presenter
            }
    }

    @ActivityScope
    @Binds
    fun bindPresenter(impl: ActionSelectionPresenter): ActionSelectionContract.Presenter

    @ActivityScope
    @Binds
    fun bindRouter(impl: ActionSelectionRouter): ActionSelectionContract.Router

}