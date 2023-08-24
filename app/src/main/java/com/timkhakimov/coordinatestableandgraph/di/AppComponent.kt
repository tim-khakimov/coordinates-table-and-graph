package com.timkhakimov.coordinatestableandgraph.di

import com.timkhakimov.coordinatestableandgraph.domain.PointsInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoriesModule::class,
        InteractorsModule::class
    ]
)
interface AppComponent {
    val pointsInteractor: PointsInteractor
}