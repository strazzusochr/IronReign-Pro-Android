package com.ironreign.app.ui.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.entity.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val repository: IronRepository
) : ViewModel() {

    val exercises: LiveData<List<Exercise>> = repository.getAllExercises().asLiveData()

    fun addExercise(name: String, description: String, muscleGroup: String) {
        viewModelScope.launch {
            repository.insertExercise(Exercise(name = name, description = description, muscleGroup = muscleGroup))
        }
    }
}
