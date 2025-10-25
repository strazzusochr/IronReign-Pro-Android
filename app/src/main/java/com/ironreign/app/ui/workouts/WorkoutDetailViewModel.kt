package com.ironreign.app.ui.workouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.entity.Exercise
import com.ironreign.app.data.entity.WorkoutSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    private val repository: IronRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val workoutId: Long = savedStateHandle.get<Long>("workoutId")!!

    val sets: LiveData<List<WorkoutSet>> = repository.getSetsForWorkout(workoutId).asLiveData()
    val allExercises: LiveData<List<Exercise>> = repository.getAllExercises().asLiveData()

    fun addWorkoutSet(exerciseId: Long, reps: Int, weight: Float) {
        viewModelScope.launch {
            repository.insertWorkoutSet(WorkoutSet(workoutId = workoutId, exerciseId = exerciseId, reps = reps, weight = weight))
        }
    }

    fun updateWorkoutSet(workoutSet: WorkoutSet) {
        viewModelScope.launch {
            repository.updateWorkoutSet(workoutSet)
        }
    }

    fun deleteWorkoutSet(workoutSet: WorkoutSet) {
        viewModelScope.launch {
            repository.deleteWorkoutSet(workoutSet)
        }
    }
}
