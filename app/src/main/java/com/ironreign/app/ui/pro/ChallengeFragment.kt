package com.ironreign.app.ui.pro

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ironreign.app.R
import com.ironreign.app.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    private val binding get() = _binding!!

    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChallengeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartChallenge.setOnClickListener { startTimer() }
        binding.btnStopChallenge.setOnClickListener { stopTimer() }
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.textTimer.text = "0"
                Toast.makeText(requireContext(), getString(R.string.challenge_completed), Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun stopTimer() {
        timer?.cancel()
        binding.textTimer.text = "30"
    }

    override fun onDestroyView() {
        stopTimer()
        super.onDestroyView()
        _binding = null
    }
}
