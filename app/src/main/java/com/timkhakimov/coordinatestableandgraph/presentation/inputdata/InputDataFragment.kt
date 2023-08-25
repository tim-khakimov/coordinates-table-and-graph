package com.timkhakimov.coordinatestableandgraph.presentation.inputdata

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.timkhakimov.coordinatestableandgraph.R
import com.timkhakimov.coordinatestableandgraph.appComponent
import com.timkhakimov.coordinatestableandgraph.data.model.Response
import com.timkhakimov.coordinatestableandgraph.databinding.FragmentInputDataBinding
import com.timkhakimov.coordinatestableandgraph.presentation.core.lazyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InputDataFragment : Fragment() {

    private var _binding: FragmentInputDataBinding? = null
    private val binding get() = _binding!!

    private val component: InputDataComponent by lazy {
        DaggerInputDataComponent.factory().create(appComponent())
    }

    private val viewModel: InputDataViewModel by lazyViewModel {
        component.createViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() = with(binding) {
        goButton.setOnClickListener {
            hideKeyboard()
            viewModel.onButtonClicked()
        }
        inputDataEditText.doAfterTextChanged {
            viewModel.onTextInput(it.toString())
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.loadingState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    binding.progressBarLayout.isVisible = it
                }
        }
        lifecycleScope.launch {
            viewModel.isEnabledButton
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    binding.goButton.isEnabled = it
                }
        }
        lifecycleScope.launch {
            viewModel.pointsResponseError
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    showError(it)
                }
        }
        lifecycleScope.launch {
            viewModel.pointsResponseSuccess
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    findNavController().navigate(
                        InputDataFragmentDirections.actionInputDataToTableAndGraph(it)
                    )
                }
        }
    }

    private fun showError(error: Response.Error<*>) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error)
            .setMessage(
                when(error) {
                    is Response.Error.Server -> error.message
                    is Response.Error.Network -> getString(R.string.network_error)
                    is Response.Error.Unknown -> getString(R.string.unknown_error)
                }
            )
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun hideKeyboard() = with(requireActivity()) {
        currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}