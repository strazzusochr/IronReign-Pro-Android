package com.ironreign.app.data

import com.ironreign.app.data.dao.ExerciseDao
import com.ironreign.app.data.dao.FoodEntryDao
import com.ironreign.app.data.dao.WorkoutDao
import com.ironreign.app.data.dao.WorkoutSetDao
import com.ironreign.app.data.entity.Exercise
import com.ironreign.app.data.entity.FoodEntry
import com.ironreign.app.data.entity.Workout
import com.ironreign.app.data.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IronRepository @Inject constructor(private val database: IronDatabase) {

    private val exerciseDao = database.exerciseDao()
    private val workoutDao = database.workoutDao()
    private val workoutSetDao = database.workoutSetDao()
    private val foodEntryDao = database.foodEntryDao()

    // --- Exercise Methods ---
    fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.getAllExercises()
    fun getExerciseById(id: Long): Flow<Exercise> = exerciseDao.getExerciseById(id)
    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insertAll(listOf(exercise))

    // --- Workout Methods ---
    fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()
    suspend fun insertWorkout(workout: Workout): Long = workoutDao.insert(workout)

    // --- WorkoutSet Methods ---
    fun getSetsForWorkout(workoutId: Long): Flow<List<WorkoutSet>> = workoutSetDao.getSetsForWorkout(workoutId)
    suspend fun insertWorkoutSet(workoutSet: WorkoutSet) = workoutSetDao.insert(workoutSet)
    suspend fun updateWorkoutSet(workoutSet: WorkoutSet) = workoutSetDao.update(workoutSet)
    suspend fun deleteWorkoutSet(workoutSet: WorkoutSet) = workoutSetDao.delete(workoutSet)

    // --- FoodEntry Methods ---
    fun getAllFoodEntries(): Flow<List<FoodEntry>> = foodEntryDao.getAllFoodEntries()
    suspend fun insertFoodEntry(foodEntry: FoodEntry) = foodEntryDao.insert(foodEntry)

    // --- Analytics ---
    fun getDailyVolumes(): Flow<List<WorkoutSetDao.DailyVolume>> = workoutSetDao.getDailyVolumes()
    data class ExerciseStats(val exercise: Exercise, val totalVolume: Float, val oneRm: Float)

    fun getExerciseStats(): Flow<List<ExerciseStats>> {
        return combine(exerciseDao.getAllExercises(), workoutSetDao.getAllSets()) { exercises, sets ->
            exercises.map { exercise -> calculateStatsForExercise(exercise, sets) }
        }
    }

    fun getStatsForExercise(exerciseId: Long): Flow<ExerciseStats> {
        return combine(getExerciseById(exerciseId), workoutSetDao.getAllSets()) { exercise, sets ->
            calculateStatsForExercise(exercise, sets)
        }
    }

    private fun calculateStatsForExercise(exercise: Exercise, allSets: List<WorkoutSet>): ExerciseStats {
        val setsForExercise = allSets.filter { it.exerciseId == exercise.id }
        val totalVolume = setsForExercise.sumOf { (it.reps * it.weight).toDouble() }.toFloat()
        val oneRm = setsForExercise.maxOfOrNull { set -> set.weight * (1f + set.reps / 30f) } ?: 0f
        return ExerciseStats(exercise, totalVolume, oneRm)
    }
}
