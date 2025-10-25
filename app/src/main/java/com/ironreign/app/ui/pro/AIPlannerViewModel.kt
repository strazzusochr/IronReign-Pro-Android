package com.ironreign.app.ui.pro

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.WorkoutPlan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class AIPlannerViewModel @Inject constructor(
    repository: IronRepository
) : ViewModel() {

    val workoutPlan: LiveData<WorkoutPlan> = repository.getAllExercises().map {
        generateWorkoutPlan(it)
    }.asLiveData()

    private fun generateWorkoutPlan(exercises: List<com.ironreign.app.data.entity.Exercise>): WorkoutPlan {
        val workoutExercises = exercises.shuffled().take(5).map {
            WorkoutPlan.WorkoutExercise(it.name, (3..5).random(), (8..12).random())
        }
        return WorkoutPlan("KI-generierter Plan", workoutExercises)
    }
}
