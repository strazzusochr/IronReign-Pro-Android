package com.ironreign.app.ui.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ironreign.app.data.IronRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    repository: IronRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val exerciseId: Long = savedStateHandle.get<Long>("exerciseId")!!

    val exerciseStats: LiveData<IronRepository.ExerciseStats> = repository.getStatsForExercise(exerciseId).asLiveData()
}
