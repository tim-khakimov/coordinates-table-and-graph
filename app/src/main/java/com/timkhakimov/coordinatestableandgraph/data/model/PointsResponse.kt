package com.timkhakimov.coordinatestableandgraph.data.model

import java.io.Serializable

data class PointsResponse(
    val points: List<Point>,
) : Serializable
