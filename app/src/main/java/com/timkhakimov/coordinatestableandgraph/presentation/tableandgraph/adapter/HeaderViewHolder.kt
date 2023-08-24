package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timkhakimov.coordinatestableandgraph.databinding.ItemHeaderBinding

class HeaderViewHolder(
    binding: ItemHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup) =
            HeaderViewHolder(
                ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}