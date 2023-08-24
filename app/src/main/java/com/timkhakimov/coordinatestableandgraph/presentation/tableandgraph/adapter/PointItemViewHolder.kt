package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timkhakimov.coordinatestableandgraph.data.model.Point
import com.timkhakimov.coordinatestableandgraph.databinding.ItemPointBinding

class PointItemViewHolder(
    private val binding: ItemPointBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(point: Point) = with(binding) {
        pointXTextView.text = point.x.toString()
        pointYTextView.text = point.y.toString()
    }

    companion object {
        fun create(parent: ViewGroup) =
            PointItemViewHolder(
                ItemPointBinding.inflate(
                    android.view.LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}