package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.timkhakimov.coordinatestableandgraph.data.model.Point
import com.timkhakimov.coordinatestableandgraph.databinding.ItemGraphBinding

class GraphItemViewHolder(
    private val binding: ItemGraphBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(points: List<Point>) = with(binding) {
        with(lineChart.xAxis) {
            setDrawGridLines(false)
            position = XAxis.XAxisPosition.BOTTOM
            axisMinimum = points.minOf { it.x }.toFloat() - OFFSET
            axisMaximum = points.maxOf { it.x }.toFloat() + OFFSET
            setDrawAxisLine(true)
        }

        with(lineChart.axisLeft) {
            setDrawGridLines(false)
            axisMinimum = points.minOf { it.y }.toFloat() - OFFSET
            axisMaximum = points.maxOf { it.y }.toFloat() + OFFSET
            setDrawAxisLine(true)
        }

        lineChart.axisRight.isEnabled = false
        lineChart.description = null
        lineChart.legend.isEnabled = false

        val dataSet = LineDataSet(
            points.map {
                Entry(it.x.toFloat(), it.y.toFloat())
            },
            ""
        )
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        lineChart.data = LineData(dataSet)
        lineChart.invalidate()
    }

    companion object {

        private const val OFFSET = 5f

        fun create(parent: ViewGroup) =
            GraphItemViewHolder(
                ItemGraphBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}