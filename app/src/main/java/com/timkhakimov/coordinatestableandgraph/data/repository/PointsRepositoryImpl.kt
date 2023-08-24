package com.timkhakimov.coordinatestableandgraph.data.repository

import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import com.timkhakimov.coordinatestableandgraph.data.model.Response
import com.timkhakimov.coordinatestableandgraph.data.model.tryCatching
import com.timkhakimov.coordinatestableandgraph.data.rest.PointsService
import com.timkhakimov.coordinatestableandgraph.domain.PointsRepository

class PointsRepositoryImpl(
    private val pointsService: PointsService
): PointsRepository {

    override suspend fun getPoints(count: Int): Response<PointsResponse> {
        return tryCatching { pointsService.getPoints(count) }
    }
}

