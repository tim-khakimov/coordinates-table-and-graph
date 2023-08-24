package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph

import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import com.timkhakimov.coordinatestableandgraph.di.AppComponent
import com.timkhakimov.coordinatestableandgraph.di.FragmentScope
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class])
@FragmentScope
interface TableAndGraphComponent {

    fun createViewModel(): TableAndGraphViewModel

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent,
            @BindsInstance pointsResponse: PointsResponse
        ): TableAndGraphComponent
    }
}