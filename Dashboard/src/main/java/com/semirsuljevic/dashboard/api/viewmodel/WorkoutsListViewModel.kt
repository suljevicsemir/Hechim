package com.semirsuljevic.dashboard.api.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.sdk.model.workout.Workout
import com.semirsuljevic.foundation.api.workouts.WorkoutsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsListViewModel @Inject constructor(
    private val workoutsApi: WorkoutsApi
): ViewModel(){

    private val _workouts = mutableStateOf(emptyList<Workout>())
    val workouts get() = _workouts.value

    fun refresh() {
        viewModelScope.launch {
            workoutsApi.getWorkouts().collectLatest {
                _workouts.value = it
            }
        }
    }

}
