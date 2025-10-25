package com.ironreign.app.ui.pro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ironreign.app.R
import com.ironreign.app.databinding.FragmentProFeaturesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProFeaturesFragment : Fragment() {

    private var _binding: FragmentProFeaturesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProFeaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardArCoach.setOnClickListener {
            findNavController().navigate(R.id.action_proFeaturesFragment_to_arCoachFragment)
        }

        binding.cardAiPlanner.setOnClickListener {
            findNavController().navigate(R.id.action_proFeaturesFragment_to_aiPlannerFragment)
        }

        binding.cardComplexAi.setOnClickListener {
            findNavController().navigate(R.id.action_proFeaturesFragment_to_complexAIAnalysisFragment)
        }

        binding.card1rmCalculator.setOnClickListener {
            findNavController().navigate(R.id.action_proFeaturesFragment_to_oneRMCalculatorFragment)
        }

        binding.cardWearables.setOnClickListener {
            findNavController().navigate(R.id.action_proFeaturesFragment_to_wearablesFragment)
        }

        binding.cardChallenge.setOnClickListener {
            findNavController().navigate(R.id.action_proFeaturesFragment_to_challengeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
