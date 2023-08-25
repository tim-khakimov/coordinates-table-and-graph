package com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.timkhakimov.coordinatestableandgraph.appComponent
import com.timkhakimov.coordinatestableandgraph.databinding.FragmentTableAndGraphBinding
import com.timkhakimov.coordinatestableandgraph.presentation.core.lazyViewModel
import com.timkhakimov.coordinatestableandgraph.presentation.tableandgraph.adapter.TableAndGraphAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TableAndGraphFragment : Fragment() {

    private var _binding: FragmentTableAndGraphBinding? = null
    private val binding get() = _binding!!

    private val component: TableAndGraphComponent by lazy {
        DaggerTableAndGraphComponent.factory().create(
            appComponent(),
            TableAndGraphFragmentArgs.fromBundle(requireArguments()).pointsResponse
        )
    }

    private val viewModel: TableAndGraphViewModel by lazyViewModel {
        component.createViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableAndGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.tableAndGraphItems
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    binding.tableAndGraphRecyclerView.adapter = TableAndGraphAdapter(it)
                }
        }
    }
}