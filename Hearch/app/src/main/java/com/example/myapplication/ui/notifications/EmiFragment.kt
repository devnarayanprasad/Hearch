package com.example.myapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color
import com.example.myapplication.databinding.FragmentEmiBinding
import com.github.mikephil.charting.charts.PieChart

import java.util.ArrayList

import java.util.HashMap




class EmiFragment : Fragment() {

    private lateinit var emiViewModel: EmiViewModel
    private var _binding: FragmentEmiBinding? = null
    private var pieChart:PieChart?=null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        emiViewModel =
            ViewModelProvider(this).get(EmiViewModel::class.java)

        _binding = FragmentEmiBinding.inflate(inflater, container, false)
        val root: View = binding.root
        pieChart=binding.pieChart

       // val textView: TextView = binding.textNotifications
        emiViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
        })
        showPieChart()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPieChart() {
        val pieEntries = ArrayList<PieEntry>()
        val label = "type"

        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()

        typeAmountMap["Loan Amount"] = 500
        typeAmountMap["Interest"] = 500

        //initializing colors for the entries
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#1FBFBE"))
        colors.add(Color.parseColor("#C52F51"))

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
    }
}