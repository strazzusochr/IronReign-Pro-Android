package com.ironreign.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ironreign.app.data.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(set: WorkoutSet)

    @Update
    suspend fun update(set: WorkoutSet)

    @Delete
    suspend fun delete(set: WorkoutSet)

    @Query("SELECT * FROM workout_sets WHERE workoutId = :workoutId ORDER BY timestamp DESC")
    fun getSetsForWorkout(workoutId: Long): Flow<List<WorkoutSet>>

    @Query("SELECT * FROM workout_sets")
    fun getAllSets(): Flow<List<WorkoutSet>>

    data class DailyVolume(val date: String, val totalVolume: Float)

    @Query("SELECT date(timestamp/1000, 'unixepoch') as date, SUM(reps * weight) as totalVolume FROM workout_sets GROUP BY date ORDER BY date ASC")
    fun getDailyVolumes(): Flow<List<DailyVolume>>
}
