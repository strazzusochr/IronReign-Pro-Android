package com.ironreign.app.ui.pro

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ironreign.app.R
import com.ironreign.app.databinding.FragmentWearablesBinding

class WearablesFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentWearablesBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    private var stepCounterSensor: Sensor? = null
    private var isTracking = false

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startTracking()
        } else {
            Toast.makeText(requireContext(), "Berechtigung für Aktivitätsdaten verweigert", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWearablesBinding.inflate(inflater, container, false)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (stepCounterSensor == null) {
            Toast.makeText(requireContext(), getString(R.string.wearables_not_available), Toast.LENGTH_LONG).show()
            binding.btnStartTracking.isEnabled = false
            binding.btnStopTracking.isEnabled = false
        }

        binding.btnStartTracking.setOnClickListener { checkPermissionAndStart() }
        binding.btnStopTracking.setOnClickListener { stopTracking() }
    }

    private fun checkPermissionAndStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    startTracking()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                }
            }
        } else {
            startTracking()
        }
    }

    private fun startTracking() {
        if (isTracking) return
        isTracking = true
        stepCounterSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
            Toast.makeText(requireContext(), "Tracking gestartet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopTracking() {
        if (!isTracking) return
        isTracking = false
        sensorManager.unregisterListener(this)
        Toast.makeText(requireContext(), "Tracking gestoppt", Toast.LENGTH_SHORT).show()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val steps = event.values[0].toInt()
            binding.textStepCount.text = getString(R.string.step_count, steps)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for this implementation
    }

    override fun onDestroyView() {
        stopTracking()
        super.onDestroyView()
        _binding = null
    }
}
