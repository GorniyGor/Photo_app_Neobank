package com.recan.photo_app.presentation.action_selection.di

import android.content.Context
import com.recan.photo_app.di.ActivityScope
import com.recan.photo_app.presentation.action_selection.ActionSelectionActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@ActivityScope
@Subcomponent(
    modules = [ActionSelectionActivityModule::class]
)
interface ActionSelectionActivityComponent {

    fun inject(activity: ActionSelectionActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @Named("activityContext")
            @BindsInstance activityContext: Context
        ): ActionSelectionActivityComponent
    }
}