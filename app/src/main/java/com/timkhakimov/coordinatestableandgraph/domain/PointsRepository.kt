package com.timkhakimov.coordinatestableandgraph.domain

import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import com.timkhakimov.coordinatestableandgraph.data.model.Response

interface PointsRepository {

    suspend fun getPoints(count: Int): Response<PointsResponse>
}