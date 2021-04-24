package com.recan.photo_app.di

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    fun getErrorToast(context: Context): Toast =
        Toast.makeText(context, "Something goes wrong, please try again", Toast.LENGTH_LONG)
}