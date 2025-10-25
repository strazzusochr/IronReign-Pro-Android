package com.ironreign.app.ui.workouts

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ironreign.app.R
import kotlin.Int
import kotlin.Long

public class WorkoutsFragmentDirections private constructor() {
  private data class ActionWorkoutsFragmentToWorkoutDetailFragment(
    public val workoutId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_workoutsFragment_to_workoutDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("workoutId", this.workoutId)
        return result
      }
  }

  public companion object {
    public fun actionWorkoutsFragmentToWorkoutDetailFragment(workoutId: Long): NavDirections =
        ActionWorkoutsFragmentToWorkoutDetailFragment(workoutId)
  }
}
