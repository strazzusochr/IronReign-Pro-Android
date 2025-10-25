package com.ironreign.app.ui.progress

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ironreign.app.R
import com.ironreign.app.data.dao.WorkoutSetDao
import com.ironreign.app.databinding.FragmentProgressBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProgressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
        viewModel.dailyVolumes.observe(viewLifecycleOwner) { dailyVolumes ->
            if (dailyVolumes.isNotEmpty()) {
                updateChart(dailyVolumes)
            }
        }
    }

    private fun setupChart() {
        binding.lineChartProgress.apply {
            description.isEnabled = false
            setNoDataText(getString(R.string.no_chart_data))
            setNoDataTextColor(Color.WHITE)
            setDrawGridBackground(false)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textColor = Color.WHITE
            xAxis.valueFormatter = DateAxisValueFormatter(emptyList())

            axisLeft.textColor = Color.WHITE
            axisRight.isEnabled = false
            legend.textColor = Color.WHITE
        }
    }

    private fun updateChart(dailyVolumes: List<WorkoutSetDao.DailyVolume>) {
        val entries = dailyVolumes.mapIndexed { index, volume ->
            Entry(index.toFloat(), volume.totalVolume)
        }

        (binding.lineChartProgress.xAxis.valueFormatter as DateAxisValueFormatter).updateDates(dailyVolumes)

        val dataSet = LineDataSet(entries, "Trainingsvolumen").apply {
            color = ContextCompat.getColor(requireContext(), R.color.colorSecondary)
            valueTextColor = Color.WHITE
            setCircleColor(color)
            lineWidth = 2f
        }

        val lineData = LineData(dataSet)
        binding.lineChartProgress.data = lineData
        binding.lineChartProgress.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class DateAxisValueFormatter(private var dailyVolumes: List<WorkoutSetDao.DailyVolume>) : ValueFormatter() {
        private val format = SimpleDateFormat("dd.MM", Locale.GERMAN)
        private val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN)

        fun updateDates(newVolumes: List<WorkoutSetDao.DailyVolume>) {
            this.dailyVolumes = newVolumes
        }

        override fun getAxisLabel(value: Float, axis: com.github.mikephil.charting.components.AxisBase?): String {
            val index = value.toInt()
            if (index >= 0 && index < dailyVolumes.size) {
                val date = inputFormat.parse(dailyVolumes[index].date)
                return if (date != null) format.format(date) else ""
            }
            return ""
        }
    }
}
