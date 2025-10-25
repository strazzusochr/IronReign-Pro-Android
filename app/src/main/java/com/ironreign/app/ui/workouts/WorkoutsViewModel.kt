package com.ironreign.app.ui.workouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.entity.Workout
import com.ironreign.app.data.entity.WorkoutSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val repository: IronRepository
) : ViewModel() {

    val workouts: LiveData<List<Workout>> = repository.getAllWorkouts().asLiveData()

    fun addWorkout(name: String) {
        viewModelScope.launch {
            repository.insertWorkout(Workout(name = name))
        }
    }

    fun addSampleWorkout() {
        viewModelScope.launch {
            val workoutId = repository.insertWorkout(Workout(name = "Beispiel: Beintraining"))
            repository.insertWorkoutSet(WorkoutSet(workoutId = workoutId, exerciseId = 2, reps = 10, weight = 100f)) // Squat
            repository.insertWorkoutSet(WorkoutSet(workoutId = workoutId, exerciseId = 2, reps = 8, weight = 110f))
            repository.insertWorkoutSet(WorkoutSet(workoutId = workoutId, exerciseId = 3, reps = 12, weight = 150f)) // Deadlift
            repository.insertWorkoutSet(WorkoutSet(workoutId = workoutId, exerciseId = 3, reps = 10, weight = 160f))
        }
    }
}
