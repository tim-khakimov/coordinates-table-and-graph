package com.timkhakimov.coordinatestableandgraph.presentation.inputdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timkhakimov.coordinatestableandgraph.data.model.PointsResponse
import com.timkhakimov.coordinatestableandgraph.data.model.Response
import com.timkhakimov.coordinatestableandgraph.data.model.onError
import com.timkhakimov.coordinatestableandgraph.data.model.onFinish
import com.timkhakimov.coordinatestableandgraph.data.model.onSuccess
import com.timkhakimov.coordinatestableandgraph.domain.PointsInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InputDataViewModel @Inject constructor(
    private val pointsInteractor: PointsInteractor
) : ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState: Flow<Boolean> = _loadingState.asStateFlow()

    private val _pointsResponseSuccess = Channel<PointsResponse>()
    val pointsResponseSuccess: Flow<PointsResponse> = _pointsResponseSuccess.receiveAsFlow()

    private val _pointsResponseError = Channel<Response.Error<*>>()
    val pointsResponseError: Flow<Response.Error<*>> = _pointsResponseError.receiveAsFlow()

    private val _inputText = MutableStateFlow("")
    val isEnabledButton: Flow<Boolean> = _inputText.asStateFlow().map { it.isNotEmpty() }

    fun onButtonClicked() {
        viewModelScope.launch {
            _loadingState.emit(true)
            pointsInteractor
                .getPoints(_inputText.value.toInt())
                .onSuccess {
                    _pointsResponseSuccess.send(it)
                }
                .onError {
                    _pointsResponseError.send(it)
                }
                .onFinish {
                    _loadingState.emit(false)
                }
        }
    }

    fun onTextInput(inputText: String) {
        _inputText.value = inputText
    }
}