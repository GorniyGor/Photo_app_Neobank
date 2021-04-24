package com.recan.photo_app.presentation.gallery.di

import com.recan.photo_app.di.ActivityScope
import com.recan.photo_app.presentation.gallery.GalleryActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [GalleryActivityModule::class]
)
interface GalleryActivityComponent {

    fun inject(activity: GalleryActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): GalleryActivityComponent
    }
}