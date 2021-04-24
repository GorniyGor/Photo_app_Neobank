package com.recan.photo_app.presentation.action_selection.di

import com.recan.photo_app.di.FlowScope
import dagger.Subcomponent

@FlowScope
@Subcomponent(
    modules = [ActionSelectionFlowModule::class]
)
interface ActionSelectionFlowComponent {
    fun getActivityComponent(): ActionSelectionActivityComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActionSelectionFlowComponent
    }
}