package com.ironreign.app.ui.pro

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ironreign.app.data.IronRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComplexAIViewModel @Inject constructor(
    repository: IronRepository
) : ViewModel() {

    val stats: LiveData<List<IronRepository.ExerciseStats>> = repository.getExerciseStats().asLiveData()

}
