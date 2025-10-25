package com.ironreign.app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.entity.Exercise
import com.ironreign.app.data.entity.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    repository: IronRepository
) : ViewModel() {

    val allWorkouts: LiveData<List<Workout>> = repository.getAllWorkouts().asLiveData()
    val allExercises: LiveData<List<Exercise>> = repository.getAllExercises().asLiveData()
    
}
