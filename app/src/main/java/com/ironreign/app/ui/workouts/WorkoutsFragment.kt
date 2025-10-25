package com.ironreign.app.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.ironreign.app.R
import com.ironreign.app.databinding.FragmentWorkoutsBinding
import com.ironreign.app.ui.workouts.adapter.WorkoutAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutsViewModel by viewModels()
    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        setupMenu()

        viewModel.workouts.observe(viewLifecycleOwner) { workouts ->
            adapter.submitList(workouts)
        }
    }

    private fun setupRecyclerView() {
        adapter = WorkoutAdapter { workout ->
            val action = WorkoutsFragmentDirections.actionWorkoutsFragmentToWorkoutDetailFragment(workout.id)
            findNavController().navigate(action)
        }
        binding.recyclerViewWorkouts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWorkouts.adapter = adapter
    }

    private fun setupFab() {
        binding.fabAddWorkout.setOnClickListener {
            showAddWorkoutDialog()
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.workouts_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_add_sample_workout -> {
                        viewModel.addSampleWorkout()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showAddWorkoutDialog() {
        val editText = TextInputEditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("Neues Workout")
            .setMessage("Gib einen Namen für das neue Workout ein")
            .setView(editText)
            .setPositiveButton("Speichern") { _, _ ->
                val name = editText.text.toString().trim()
                if (name.isNotEmpty()) {
                    viewModel.addWorkout(name)
                }
            }
            .setNegativeButton("Abbrechen", null)
            .show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
