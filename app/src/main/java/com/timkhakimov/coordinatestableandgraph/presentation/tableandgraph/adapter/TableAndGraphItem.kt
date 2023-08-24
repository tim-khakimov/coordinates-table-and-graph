package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter

import com.timkhakimov.coordinatestableandgraph.data.model.Point

sealed interface TableAndGraphItem {

    object Header : TableAndGraphItem

    data class PointItem(val point: Point) : TableAndGraphItem
}