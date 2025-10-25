package com.ironreign.app.ui.nutrition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironreign.app.databinding.FragmentNutritionBinding
import com.ironreign.app.ui.nutrition.adapter.FoodEntryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NutritionFragment : Fragment() {

    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NutritionViewModel by viewModels()
    private val adapter = FoodEntryAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewFoodEntries.adapter = adapter
        binding.recyclerViewFoodEntries.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodEntries.observe(viewLifecycleOwner) { entries ->
            adapter.submitList(entries)
        }

        binding.fabAddFoodEntry.setOnClickListener {
            // For simplicity, add a hardcoded food entry. A real app would use a dialog.
            viewModel.addFoodEntry("Hähnchenbrust", 350, 60f, 5f, 10f)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
