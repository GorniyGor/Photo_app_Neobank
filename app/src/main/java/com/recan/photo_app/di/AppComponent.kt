package com.recan.photo_app.di

import android.content.Context
import com.recan.photo_app.presentation.action_selection.di.ActionSelectionFlowComponent
import com.recan.photo_app.presentation.gallery.di.GalleryFlowComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun actionSelectionFlowComponent(): ActionSelectionFlowComponent.Factory
    fun galleryFlowComponent(): GalleryFlowComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }
}


@Scope
annotation class FlowScope

@Scope
annotation class ActivityScope
