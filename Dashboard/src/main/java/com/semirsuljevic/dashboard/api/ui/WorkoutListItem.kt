package com.semirsuljevic.dashboard.api.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.semirsuljevic.foundation.api.sdk.model.workout.Workout

@Composable
fun WorkoutListItem(
    workoutInfo: Workout
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 16.dp)
            .shadow(
                elevation = 2.dp
            )
    ){
        Text(
            "Workout id: ${workoutInfo.workoutInfo.id}"
        )
        Text(
            "Distance: ${workoutInfo.workoutInfo.distance}"
        )
        Text(
            "Duration: ${workoutInfo.workoutInfo.duration}"
        )
    }
}
