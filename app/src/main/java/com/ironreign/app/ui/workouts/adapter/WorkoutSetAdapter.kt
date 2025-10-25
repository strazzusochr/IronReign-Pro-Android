package com.ironreign.app.ui.workouts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ironreign.app.data.entity.WorkoutSet
import com.ironreign.app.databinding.ItemWorkoutSetBinding

class WorkoutSetAdapter(
    private val onUpdate: (WorkoutSet) -> Unit,
    private val onDelete: (WorkoutSet) -> Unit
) : ListAdapter<WorkoutSet, WorkoutSetAdapter.WorkoutSetViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutSetViewHolder {
        val binding = ItemWorkoutSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutSetViewHolder(binding, onUpdate, onDelete)
    }

    override fun onBindViewHolder(holder: WorkoutSetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WorkoutSetViewHolder(
        private val binding: ItemWorkoutSetBinding,
        private val onUpdate: (WorkoutSet) -> Unit,
        private val onDelete: (WorkoutSet) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(set: WorkoutSet) {
            binding.textSetDetails.text = "${set.reps} reps x ${set.weight} kg"

            binding.buttonIncreaseReps.setOnClickListener {
                onUpdate(set.copy(reps = set.reps + 1))
            }
            binding.buttonDecreaseReps.setOnClickListener {
                if (set.reps > 1) onUpdate(set.copy(reps = set.reps - 1))
            }
            binding.buttonDeleteSet.setOnClickListener {
                onDelete(set)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WorkoutSet>() {
        override fun areItemsTheSame(oldItem: WorkoutSet, newItem: WorkoutSet) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: WorkoutSet, newItem: WorkoutSet) = oldItem == newItem
    }
}
