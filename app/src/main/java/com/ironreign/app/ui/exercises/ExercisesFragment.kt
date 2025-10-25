package com.ironreign.app.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironreign.app.databinding.FragmentExercisesBinding
import com.ironreign.app.ui.exercises.adapter.ExerciseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesFragment : Fragment() {

    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExercisesViewModel by viewModels()
    private lateinit var adapter: ExerciseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()

        viewModel.exercises.observe(viewLifecycleOwner) { exercises ->
            adapter.submitList(exercises)
        }
    }

    private fun setupRecyclerView() {
        adapter = ExerciseAdapter { exercise ->
            val action = ExercisesFragmentDirections.actionExercisesFragmentToExerciseDetailFragment(exercise.id)
            findNavController().navigate(action)
        }
        binding.recyclerViewExercises.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewExercises.adapter = adapter
    }

    private fun setupFab() {
        binding.fabAddExercise.setOnClickListener {
            AddExerciseDialogFragment().show(childFragmentManager, AddExerciseDialogFragment.TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
