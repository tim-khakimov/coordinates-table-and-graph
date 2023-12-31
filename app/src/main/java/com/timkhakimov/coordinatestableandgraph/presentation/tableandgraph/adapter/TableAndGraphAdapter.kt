package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TableAndGraphAdapter(
    private val items: List<TableAndGraphItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder.create(parent)
            TYPE_POINT -> PointItemViewHolder.create(parent)
            TYPE_GRAPH -> GraphItemViewHolder.create(parent)
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (val item = items[position]) {
            is TableAndGraphItem.PointItem -> (holder as PointItemViewHolder).bind(item.point)
            is TableAndGraphItem.GraphItem -> (holder as GraphItemViewHolder).bind(item.points)
            else -> {
                // Do nothing.
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            TableAndGraphItem.Header -> TYPE_HEADER
            is TableAndGraphItem.PointItem -> TYPE_POINT
            is TableAndGraphItem.GraphItem -> TYPE_GRAPH
        }
    }

    private companion object {
        const val TYPE_HEADER = 0
        const val TYPE_POINT = 1
        const val TYPE_GRAPH = 2
    }
}