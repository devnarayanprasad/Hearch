package com.example.myapplication.ui.emi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentEmiBinding
import com.github.mikephil.charting.charts.PieChart

import java.util.ArrayList

import java.util.HashMap




class EmiFragment : Fragment() {

    private  val emiViewModel by activityViewModels<EmiViewModel>()
    private var _binding: FragmentEmiBinding? = null
    private var pieChart: PieChart? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentEmiBinding.inflate(inflater, container, false).apply {

            this.emi=emiViewModel
        }
        val root: View = binding.root
        pieChart = binding.pieChart
        binding.calculateButton.setOnClickListener {
            run {
                emiViewModel.setEmiAmount();
                showPieChart(emiViewModel)

            }
        }

        showPieChart(emiViewModel)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     fun showPieChart(emiViewModel: EmiViewModel) {
        val pieEntries = ArrayList<PieEntry>()
        val label = "Interest Principal Breakup"

        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()

        typeAmountMap["Loan Amount"] = emiViewModel.emi.loanAmount.toInt()
        typeAmountMap["Interest"] = emiViewModel.emi.interestComponent.toInt()

        //initializing colors for the entries
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#C52F51"))
         colors.add(Color.parseColor("#1FBFBE"))

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, label)
        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)
        pieChart?.setData(pieData)
        pieChart?.invalidate()


        /*  val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

        }*/
    }
}