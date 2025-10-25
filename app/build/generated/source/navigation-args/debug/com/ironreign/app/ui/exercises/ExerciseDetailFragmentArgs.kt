package com.ironreign.app.ui.exercises

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class ExerciseDetailFragmentArgs(
  public val exerciseId: Long,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("exerciseId", this.exerciseId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("exerciseId", this.exerciseId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ExerciseDetailFragmentArgs {
      bundle.setClassLoader(ExerciseDetailFragmentArgs::class.java.classLoader)
      val __exerciseId : Long
      if (bundle.containsKey("exerciseId")) {
        __exerciseId = bundle.getLong("exerciseId")
      } else {
        throw IllegalArgumentException("Required argument \"exerciseId\" is missing and does not have an android:defaultValue")
      }
      return ExerciseDetailFragmentArgs(__exerciseId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        ExerciseDetailFragmentArgs {
      val __exerciseId : Long?
      if (savedStateHandle.contains("exerciseId")) {
        __exerciseId = savedStateHandle["exerciseId"]
        if (__exerciseId == null) {
          throw IllegalArgumentException("Argument \"exerciseId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"exerciseId\" is missing and does not have an android:defaultValue")
      }
      return ExerciseDetailFragmentArgs(__exerciseId)
    }
  }
}
