package com.recan.photo_app.presentation.action_selection.di

import com.recan.photo_app.di.FlowScope
import com.recan.photo_app.domain.usecases.SavePhotoInfoUseCase
import com.recan.photo_app.domain.usecases.SavePhotoInfoUseCaseImpl
import com.recan.photo_app.domain.usecases.SavePhotoUseCase
import com.recan.photo_app.domain.usecases.SavePhotoUseCaseImpl
import com.recan.photo_app.presentation.action_selection.ActionSelectionContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ActionSelectionFlowModule {

    companion object {
        @FlowScope
        @Provides
        fun bindState(): ActionSelectionContract.State = ActionSelectionContract.State()
    }

    @FlowScope
    @Binds
    fun bindPhotoUserCase(impl: SavePhotoUseCaseImpl): SavePhotoUseCase

    @FlowScope
    @Binds
    fun bindInfoUserCase(impl: SavePhotoInfoUseCaseImpl): SavePhotoInfoUseCase

}