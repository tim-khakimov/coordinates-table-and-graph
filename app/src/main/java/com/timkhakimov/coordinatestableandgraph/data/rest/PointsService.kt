package com.timkhakimov.coordinatestableandgraph.data.rest

import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PointsService {

    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count : Int): PointsResponse
}