package com.recan.photo_app.presentation.gallery.di

import com.recan.photo_app.di.ActivityScope
import com.recan.photo_app.di.CommonModule
import com.recan.photo_app.presentation.base.PresenterFactory
import com.recan.photo_app.presentation.gallery.GalleryContract
import com.recan.photo_app.presentation.gallery.GalleryPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [CommonModule::class])
interface GalleryActivityModule {

    companion object {

        @ActivityScope
        @Provides
        fun getPresenterFactory(presenter: GalleryContract.Presenter) =
            object : PresenterFactory<GalleryContract.View, GalleryContract.Presenter> {
                override fun get(): GalleryContract.Presenter = presenter
            }
    }

    @ActivityScope
    @Binds
    fun bindPresenter(impl: GalleryPresenter): GalleryContract.Presenter
}