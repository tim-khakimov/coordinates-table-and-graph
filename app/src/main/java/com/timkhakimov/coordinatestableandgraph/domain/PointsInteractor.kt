package com.timkhakimov.coordinatestableandgraph.domain

import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import com.timkhakimov.coordinatestableandgraph.data.model.Response

class PointsInteractor(
    private val pointsRepository: PointsRepository
) {

    suspend fun getPoints(count: Int): Response<PointsResponse> =
        pointsRepository.getPoints(count)
}