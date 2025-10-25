package com.ironreign.app.ui.pro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ironreign.app.data.WorkoutPlan
import com.ironreign.app.databinding.ItemTrainingDayBinding

class WorkoutExerciseAdapter : ListAdapter<WorkoutPlan.WorkoutExercise, WorkoutExerciseAdapter.ViewHolder>(WorkoutExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrainingDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemTrainingDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: WorkoutPlan.WorkoutExercise) {
            binding.textExerciseName.text = exercise.exerciseName
            binding.textSets.text = "${exercise.sets} Sätze"
            binding.textReps.text = "${exercise.reps} Wdh."
        }
    }
}

class WorkoutExerciseDiffCallback : DiffUtil.ItemCallback<WorkoutPlan.WorkoutExercise>() {
    override fun areItemsTheSame(oldItem: WorkoutPlan.WorkoutExercise, newItem: WorkoutPlan.WorkoutExercise): Boolean {
        return oldItem.exerciseName == newItem.exerciseName
    }

    override fun areContentsTheSame(oldItem: WorkoutPlan.WorkoutExercise, newItem: WorkoutPlan.WorkoutExercise): Boolean {
        return oldItem == newItem
    }
}
