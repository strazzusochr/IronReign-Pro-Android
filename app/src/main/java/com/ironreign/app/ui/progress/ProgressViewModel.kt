package com.ironreign.app.ui.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.dao.WorkoutSetDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    repository: IronRepository
) : ViewModel() {

    val dailyVolumes: LiveData<List<WorkoutSetDao.DailyVolume>> = repository.getDailyVolumes().asLiveData()

}
