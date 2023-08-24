package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph

import androidx.lifecycle.ViewModel
import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter.TableAndGraphItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class TableAndGraphViewModel @Inject constructor(
    private val pointsResponse: PointsResponse
) : ViewModel() {

    private val _tableAndGraphItems = MutableStateFlow<List<TableAndGraphItem>>(
        mutableListOf<TableAndGraphItem>().apply {
            add(TableAndGraphItem.Header)
            addAll(pointsResponse.points.map { TableAndGraphItem.PointItem(it) })
            add(TableAndGraphItem.GraphItem(pointsResponse.points.sortedBy { it.x }))
        }
    )
    val tableAndGraphItems = _tableAndGraphItems.asStateFlow()
}