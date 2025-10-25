package com.ironreign.app.ui.workouts

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class WorkoutDetailFragmentArgs(
  public val workoutId: Long,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("workoutId", this.workoutId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("workoutId", this.workoutId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): WorkoutDetailFragmentArgs {
      bundle.setClassLoader(WorkoutDetailFragmentArgs::class.java.classLoader)
      val __workoutId : Long
      if (bundle.containsKey("workoutId")) {
        __workoutId = bundle.getLong("workoutId")
      } else {
        throw IllegalArgumentException("Required argument \"workoutId\" is missing and does not have an android:defaultValue")
      }
      return WorkoutDetailFragmentArgs(__workoutId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): WorkoutDetailFragmentArgs {
      val __workoutId : Long?
      if (savedStateHandle.contains("workoutId")) {
        __workoutId = savedStateHandle["workoutId"]
        if (__workoutId == null) {
          throw IllegalArgumentException("Argument \"workoutId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"workoutId\" is missing and does not have an android:defaultValue")
      }
      return WorkoutDetailFragmentArgs(__workoutId)
    }
  }
}
