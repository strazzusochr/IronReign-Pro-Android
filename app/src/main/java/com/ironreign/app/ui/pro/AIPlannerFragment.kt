package com.ironreign.app.ui.pro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironreign.app.databinding.FragmentAiPlannerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AIPlannerFragment : Fragment() {

    private var _binding: FragmentAiPlannerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AIPlannerViewModel by viewModels()
    private lateinit var adapter: WorkoutExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAiPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WorkoutExerciseAdapter()
        binding.recyclerTrainingPlan.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTrainingPlan.adapter = adapter

        viewModel.workoutPlan.observe(viewLifecycleOwner) { plan ->
            if (plan == null || plan.exercises.isEmpty()) {
                binding.recyclerTrainingPlan.visibility = View.GONE
                binding.textNoPlan.visibility = View.VISIBLE
            } else {
                binding.recyclerTrainingPlan.visibility = View.VISIBLE
                binding.textNoPlan.visibility = View.GONE
                adapter.submitList(plan.exercises)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
