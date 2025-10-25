package com.ironreign.app.ui.pro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ironreign.app.R
import com.ironreign.app.data.IronRepository.ExerciseStats
import com.ironreign.app.databinding.ItemExerciseStatsBinding

class ComplexAIAdapter : ListAdapter<ExerciseStats, ComplexAIAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExerciseStatsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemExerciseStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stats: ExerciseStats) {
            binding.textExerciseName.text = stats.exercise.name
            binding.textTrainingVolume.text = binding.root.context.getString(R.string.training_volume, stats.totalVolume)
            binding.textOneRm.text = binding.root.context.getString(R.string.estimated_one_rm, stats.oneRm)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ExerciseStats>() {
        override fun areItemsTheSame(oldItem: ExerciseStats, newItem: ExerciseStats): Boolean {
            return oldItem.exercise.id == newItem.exercise.id
        }

        override fun areContentsTheSame(oldItem: ExerciseStats, newItem: ExerciseStats): Boolean {
            return oldItem == newItem
        }
    }
}
