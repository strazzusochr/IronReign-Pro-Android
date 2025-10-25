package com.ironreign.app.ui.pro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ironreign.app.R
import com.ironreign.app.databinding.FragmentOneRmCalculatorBinding

class OneRMCalculatorFragment : Fragment() {

    private var _binding: FragmentOneRmCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneRmCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalculate.setOnClickListener {
            calculateOneRepMax()
        }
    }

    private fun calculateOneRepMax() {
        val weightStr = binding.editTextWeight.text.toString()
        val repsStr = binding.editTextReps.text.toString()

        if (weightStr.isEmpty() || repsStr.isEmpty()) {
            Toast.makeText(requireContext(), "Bitte Gewicht und Wiederholungen eingeben", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toFloatOrNull()
        val reps = repsStr.toIntOrNull()

        if (weight == null || reps == null || weight <= 0 || reps <= 0) {
            Toast.makeText(requireContext(), "Ungültige Eingabe", Toast.LENGTH_SHORT).show()
            return
        }

        // Epley formula
        val oneRepMax = weight * (1f + reps / 30f)

        binding.textOneRmResult.text = getString(R.string.one_rm_result, oneRepMax)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
