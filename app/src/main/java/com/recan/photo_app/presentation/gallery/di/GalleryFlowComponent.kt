package com.recan.photo_app.presentation.gallery.di

import com.recan.photo_app.di.FlowScope
import dagger.Subcomponent

@FlowScope
@Subcomponent(
    modules = [GalleryFlowModule::class]
)
interface GalleryFlowComponent {
    fun getActivityComponent(): GalleryActivityComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): GalleryFlowComponent
    }
}