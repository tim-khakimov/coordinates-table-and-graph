package com.timkhakimov.coordinatestableandgraph.presentation.inputdata

import com.timkhakimov.coordinatestableandgraph.di.AppComponent
import com.timkhakimov.coordinatestableandgraph.di.FragmentScope
import dagger.Component

@Component(dependencies = [AppComponent::class])
@FragmentScope
interface InputDataComponent {

    fun createViewModel(): InputDataViewModel

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): InputDataComponent
    }
}