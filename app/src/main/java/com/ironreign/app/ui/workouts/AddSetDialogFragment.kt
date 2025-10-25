package com.ironreign.app.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ironreign.app.data.entity.Exercise
import com.ironreign.app.databinding.DialogAddSetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddSetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutDetailViewModel by viewModels({ requireParentFragment() })
    private var selectedExercise: Exercise? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogAddSetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allExercises.observe(viewLifecycleOwner) { exercises ->
            val exerciseNames = exercises.map { it.name }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, exerciseNames)
            binding.autoCompleteExercise.setAdapter(adapter)
            binding.autoCompleteExercise.setOnItemClickListener { _, _, position, _ ->
                selectedExercise = exercises[position]
            }
        }

        binding.buttonSaveSet.setOnClickListener { saveSet() }
    }

    private fun saveSet() {
        val weightStr = binding.inputEditTextWeight.text.toString()
        val repsStr = binding.inputEditTextReps.text.toString()

        if (selectedExercise == null || weightStr.isEmpty() || repsStr.isEmpty()) {
            Toast.makeText(requireContext(), "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toFloatOrNull()
        val reps = repsStr.toIntOrNull()

        if (weight == null || reps == null || weight <= 0 || reps <= 0) {
            Toast.makeText(requireContext(), "Ungültige Eingabe für Gewicht oder Wiederholungen", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.addWorkoutSet(selectedExercise!!.id, reps, weight)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AddSetDialogFragment"
    }
}
