package com.ironreign.app.ui.nutrition

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ironreign.app.data.IronRepository
import com.ironreign.app.data.entity.FoodEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val repository: IronRepository
) : ViewModel() {

    val foodEntries: LiveData<List<FoodEntry>> = repository.getAllFoodEntries().asLiveData()

    fun addFoodEntry(name: String, calories: Int, protein: Float, carbs: Float, fat: Float) {
        viewModelScope.launch {
            repository.insertFoodEntry(FoodEntry(name = name, calories = calories, protein = protein, carbs = carbs, fat = fat))
        }
    }
}
