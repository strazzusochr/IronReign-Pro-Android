package com.ironreign.app.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironreign.app.databinding.FragmentWorkoutDetailBinding
import com.ironreign.app.ui.workouts.adapter.WorkoutSetAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutDetailFragment : Fragment() {

    private var _binding: FragmentWorkoutDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutDetailViewModel by viewModels()
    private lateinit var adapter: WorkoutSetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWorkoutDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.sets.observe(viewLifecycleOwner) { sets ->
            adapter.submitList(sets)
        }

        binding.fabAddSet.setOnClickListener {
            AddSetDialogFragment().show(childFragmentManager, AddSetDialogFragment.TAG)
        }
    }

    private fun setupRecyclerView() {
        adapter = WorkoutSetAdapter(
            onUpdate = { viewModel.updateWorkoutSet(it) },
            onDelete = { viewModel.deleteWorkoutSet(it) }
        )
        binding.recyclerViewSets.adapter = adapter
        binding.recyclerViewSets.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
