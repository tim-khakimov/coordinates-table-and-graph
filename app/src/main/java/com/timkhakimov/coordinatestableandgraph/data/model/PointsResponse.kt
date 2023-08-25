package com.timkhakimov.coordinatestableandgraph.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointsResponse(
    val points: List<Point>,
) : Parcelable
