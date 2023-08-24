package com.timkhakimov.coordinatestableandgraph.di

import com.timkhakimov.coordinatestableandgraph.data.repository.PointsRepositoryImpl
import com.timkhakimov.coordinatestableandgraph.data.rest.PointsService
import com.timkhakimov.coordinatestableandgraph.domain.PointsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun providePointsRepository(pointsService: PointsService): PointsRepository {
        return PointsRepositoryImpl(pointsService)
    }
}