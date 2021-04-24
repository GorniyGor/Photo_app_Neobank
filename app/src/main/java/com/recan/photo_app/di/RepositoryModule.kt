package com.recan.photo_app.di

import android.content.Context
import androidx.room.Room
import com.recan.photo_app.data.database.AppDatabase
import com.recan.photo_app.data.database.PhotoInfoDao
import com.recan.photo_app.data.repository.GalleryRepository
import com.recan.photo_app.domain.repository.IGalleryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun getRepository(impl: GalleryRepository): IGalleryRepository

    companion object {

        @Singleton
        @Provides
        fun provideDatabase(applicationContext: Context): AppDatabase =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "PHOTOS_APP_DATABASE"
            ).build()

        @Singleton
        @Provides
        fun providePhotoDao(database: AppDatabase): PhotoInfoDao = database.photoDao()
    }


}