package com.ironreign.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ironreign.app.data.dao.ExerciseDao
import com.ironreign.app.data.dao.FoodEntryDao
import com.ironreign.app.data.dao.WorkoutDao
import com.ironreign.app.data.dao.WorkoutSetDao
import com.ironreign.app.data.entity.Exercise
import com.ironreign.app.data.entity.FoodEntry
import com.ironreign.app.data.entity.Workout
import com.ironreign.app.data.entity.WorkoutSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class, Workout::class, WorkoutSet::class, FoodEntry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class IronDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutSetDao(): WorkoutSetDao
    abstract fun foodEntryDao(): FoodEntryDao

    companion object {
        @Volatile private var INSTANCE: IronDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): IronDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, IronDatabase::class.java, "ironreign_database")
                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.exerciseDao())
                }
            }
        }

        suspend fun populateDatabase(exerciseDao: ExerciseDao) {
            val exercises = listOf(
                Exercise(name = "Bankdrücken", description = "Grundübung für Brust, vordere Schulter und Trizeps.", muscleGroup = "Brust"),
                Exercise(name = "Kniebeuge", description = "König der Beinübungen, trainiert den gesamten Unterkörper.", muscleGroup = "Beine"),
                Exercise(name = "Kreuzheben", description = "Ganzkörperübung mit Fokus auf den Rücken und die hintere Kette.", muscleGroup = "Rücken"),
                Exercise(name = "Klimmzug", description = "Schwergewichtsübung für den oberen Rücken und Bizeps.", muscleGroup = "Rücken"),
                Exercise(name = "Schulterdrücken", description = "Grundübung für den Aufbau von Schultermuskulatur.", muscleGroup = "Schultern")
            )
            exerciseDao.insertAll(exercises)
        }
    }
}
