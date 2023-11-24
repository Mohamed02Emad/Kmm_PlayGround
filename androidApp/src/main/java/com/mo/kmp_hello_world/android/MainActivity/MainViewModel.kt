package com.mo.kmp_hello_world.android.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mo.kmp_hello_world.data.api.RequestState
import com.mo.kmp_hello_world.data.models.RocketLaunch
import com.mo.kmp_hello_world.data.repositories.RocketsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _rocketsList = MutableStateFlow<List<RocketLaunch>>(listOf())
    val rocketsList: StateFlow<List<RocketLaunch>> get() = _rocketsList

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        viewModelScope.launch {
            RocketsRepository().getLastSuccessfulLaunchDate().collect { state ->
                when (state) {
                    is RequestState.Error -> {
                        _isLoading.update { false }
                    }

                    is RequestState.Loading -> {
                        _isLoading.update { true }
                    }

                    is RequestState.Success -> {
                        state.data?.let { data ->
                            _rocketsList.update { list -> list + data }
                            _isLoading.update { false }
                        }
                    }
                }

            }
        }
    }
}

