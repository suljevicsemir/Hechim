package com.semirsuljevic.dashboard.api.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.dashboard.api.viewmodel.WorkoutsListViewModel

@Composable
fun WorkoutsScreen(
    viewModel: WorkoutsListViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit){
        viewModel.refresh()
    }

    LazyColumn {
        item {
            Text("These are your trips")
        }
        items(viewModel.workouts.size) {
            WorkoutListItem(workoutInfo = viewModel.workouts[it])
        }
    }
}
