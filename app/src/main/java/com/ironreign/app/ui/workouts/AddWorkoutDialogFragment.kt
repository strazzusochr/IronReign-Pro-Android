package com.ironreign.app.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ironreign.app.databinding.DialogAddWorkoutBinding

/**
 * Bottom sheet dialog for creating a new workout.  Users enter a
 * simple name and the [WorkoutsViewModel] handles persisting it.
 */
class AddWorkoutDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddWorkoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutsViewModel by lazy {
        requireParentFragment().let { parent ->
            androidx.lifecycle.ViewModelProvider(parent)[WorkoutsViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            val name = binding.inputName.editText?.text?.toString()?.trim() ?: ""
            if (name.isNotEmpty()) {
                viewModel.addWorkout(name)
                dismiss()
            } else {
                binding.inputName.error = "Bitte gib einen Namen ein"
            }
        }
        binding.buttonCancel.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AddWorkoutDialogFragment"
    }
}