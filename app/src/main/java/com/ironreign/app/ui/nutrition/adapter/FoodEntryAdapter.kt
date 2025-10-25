package com.ironreign.app.ui.nutrition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ironreign.app.data.entity.FoodEntry
import com.ironreign.app.databinding.ItemFoodEntryBinding

class FoodEntryAdapter : ListAdapter<FoodEntry, FoodEntryAdapter.FoodEntryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodEntryViewHolder {
        val binding = ItemFoodEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodEntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodEntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FoodEntryViewHolder(private val binding: ItemFoodEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodEntry: FoodEntry) {
            binding.textFoodName.text = foodEntry.name
            binding.textFoodMacros.text = "P: ${foodEntry.protein}g, C: ${foodEntry.carbs}g, F: ${foodEntry.fat}g, ${foodEntry.calories}kcal"
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FoodEntry>() {
        override fun areItemsTheSame(oldItem: FoodEntry, newItem: FoodEntry) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: FoodEntry, newItem: FoodEntry) = oldItem == newItem
    }
}
