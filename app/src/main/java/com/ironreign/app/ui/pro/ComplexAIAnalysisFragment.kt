package com.ironreign.app.ui.pro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironreign.app.databinding.FragmentComplexAiAnalysisBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComplexAIAnalysisFragment : Fragment() {

    private var _binding: FragmentComplexAiAnalysisBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ComplexAIViewModel by viewModels()
    private lateinit var adapter: ComplexAIAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplexAiAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ComplexAIAdapter()
        binding.recyclerStats.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerStats.adapter = adapter

        viewModel.stats.observe(viewLifecycleOwner) { stats ->
            if (stats.isNullOrEmpty()) {
                binding.recyclerStats.visibility = View.GONE
                binding.textNoStats.visibility = View.VISIBLE
            } else {
                binding.recyclerStats.visibility = View.VISIBLE
                binding.textNoStats.visibility = View.GONE
                adapter.submitList(stats)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
