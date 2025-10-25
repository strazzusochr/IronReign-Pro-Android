package com.ironreign.app.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ironreign.app.databinding.DialogAddExerciseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExerciseDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddExerciseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExercisesViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogAddExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            val name = binding.inputEditTextName.text.toString().trim()
            val desc = binding.inputEditTextDescription.text.toString().trim()
            if (name.isNotEmpty()) {
                viewModel.addExercise(name, desc, "Custom")
                dismiss()
            } else {
                binding.inputLayoutName.error = "Name darf nicht leer sein"
            }
        }
        binding.buttonCancel.setOnClickListener { dismiss() }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AddExerciseDialog"
    }
}
