package com.ironreign.app.ui.exercises

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ironreign.app.R
import kotlin.Int
import kotlin.Long

public class ExercisesFragmentDirections private constructor() {
  private data class ActionExercisesFragmentToExerciseDetailFragment(
    public val exerciseId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_exercisesFragment_to_exerciseDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("exerciseId", this.exerciseId)
        return result
      }
  }

  public companion object {
    public fun actionExercisesFragmentToExerciseDetailFragment(exerciseId: Long): NavDirections =
        ActionExercisesFragmentToExerciseDetailFragment(exerciseId)
  }
}
