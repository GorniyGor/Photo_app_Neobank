package com.recan.photo_app.presentation.gallery.di

import com.recan.photo_app.di.FlowScope
import com.recan.photo_app.domain.usecases.GetPhotoListUseCase
import com.recan.photo_app.domain.usecases.GetPhotoListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface GalleryFlowModule {

    @FlowScope
    @Binds
    fun bindUserCase(impl: GetPhotoListUseCaseImpl): GetPhotoListUseCase
}