package com.ironreign.app.data

/**
 * A simple data class representing a generated workout plan.  It consists
 * of a name and a list of exercises to perform.  This is a basic
 * structure; a more advanced version might include details about rest
 * periods, warm‑ups, and progression.
 */
data class WorkoutPlan(
    val name: String,
    val exercises: List<WorkoutExercise>
) {
    /**
     * Represents a single exercise within a workout plan, including the
     * name of the exercise and the target number of sets and reps.
     */
    data class WorkoutExercise(
        val exerciseName: String,
        val sets: Int,
        val reps: Int
    )
}
