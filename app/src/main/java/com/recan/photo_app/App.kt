package com.recan.photo_app

import android.app.Application
import com.recan.photo_app.di.AppComponent
import com.recan.photo_app.di.DaggerAppComponent

class App : Application() {

    private lateinit var applicationComponent: AppComponent

    @Synchronized
    fun getApplicationComponent() = applicationComponent

    override fun onCreate() {

        applicationComponent = DaggerAppComponent.factory().create(applicationContext)

        super.onCreate()
    }
//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        return DaggerAppComponent.builder(
//            applicationContext
//        ).build()
//    }
}