package com.example.myapplication.ui.bmi

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBmiBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class Bmi : Fragment() {

    private val bmiViewModel by activityViewModels<BmiViewModel>()
    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!
    private var chart: PieChart? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBmiBinding.inflate(inflater, container, false).apply {

            this.bmi = bmiViewModel
        }

        val spinnerFeet: Spinner = binding.spinnerFeet
// Create an ArrayAdapter using the string array and a default spinner layout
        activity?.baseContext?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.heightInFeet,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinnerFeet.adapter = adapter
            }
        }
        spinnerFeet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override
            fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {

                bmiViewModel.bmi.heightFeetUS = position.toLong()
            }

            override
            fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }


        val spinnerInch: Spinner = binding.spinnerInch

// Create an ArrayAdapter using the string array and a default spinner layout
        activity?.baseContext?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.heightInInch,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinnerInch.adapter = adapter
            }
        }


        spinnerInch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override
            fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {

                bmiViewModel.bmi.heightInchUS = position.toLong()
            }

            override
            fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }


        val root: View = binding.root
        chart = binding.bmiChart

        binding.calculateBMI.setOnClickListener {
            run {
                bmiViewModel.setBMI()
                hideKeyboard()
                showBmiChart()

            }
        }
        showBmiChart()
        return root
    }

    fun Fragment.hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    fun showBmiChart() {

        chart?.setBackgroundColor(Color.WHITE)

        chart?.setUsePercentValues(true)
        chart?.description?.isEnabled = false

        chart?.setDrawCenterText(true)

        chart?.isRotationEnabled = false
        chart?.isHighlightPerTapEnabled = true
        chart?.maxAngle = 180f // HALF CHART
        chart?.rotationAngle = 180f
        chart?.holeRadius = 45f

        chart?.setCenterTextOffset(0f, -20f)

        setData()


        ///
        chart?.holeRadius = 55f
        chart?.textAlignment = View.TEXT_ALIGNMENT_GRAVITY
        chart?.setExtraOffsets(15f, 5f, 5f, 5f)
        chart?.setEntryLabelColor(Color.BLACK)
        chart?.setEntryLabelTextSize(11f)
        //chart?.setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
        chart?.setUsePercentValues(true)
        chart?.legend?.isEnabled = false
        chart?.description?.isEnabled = false
        chart?.isRotationEnabled = true
        chart?.dragDecelerationFrictionCoef = 0.9f
        //chart?.rotationAngle = 220f
        chart?.isHighlightPerTapEnabled = true
        //chart?.animateY(1000, Easing.EaseInOutQuad)

        chart?.setHoleColor(Color.WHITE)
    }

    fun setData() {

        var values: ArrayList<PieEntry> = ArrayList<PieEntry>()
        values.add(PieEntry(20f, "18.5 Underweight"))
        values.add(PieEntry(30f, "24.9 Normal"))
        values.add(PieEntry(30f, "29.9 Overweight"))
        values.add(PieEntry(20f, "30 Obese"))

        var dataSet: PieDataSet = PieDataSet(values, "BMI Range")
        dataSet.sliceSpace = 10f
        dataSet.selectionShift = 5f
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.rgb(236, 158, 23))
        colors.add(Color.rgb(45, 194, 34))
        colors.add(Color.rgb(255, 215, 55))
        colors.add(Color.rgb(236, 74, 23))

        dataSet.colors = colors
        dataSet.selectionShift = 0f

        var data: PieData = PieData(dataSet)
        // data.setValueFormatter(LargeValueFormatter());
        data.setValueTextSize(13f)
        data.setValueTextColor(Color.BLACK)
        data.setDrawValues(false)
        chart?.data = data
        chart?.invalidate()
    }


}