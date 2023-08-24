package com.timkhakimov.coordinatestableandgraph.di

import com.timkhakimov.coordinatestableandgraph.domain.PointsInteractor
import com.timkhakimov.coordinatestableandgraph.domain.PointsRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorsModule {

    @Provides
    fun providePointsInteractor(pointsRepository: PointsRepository): PointsInteractor {
        return PointsInteractor(pointsRepository)
    }
}