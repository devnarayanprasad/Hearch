package com.example.myapplication.ui.compounding

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableDouble
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSipBinding
import com.example.myapplication.model.COMPOUNDING
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


/**
 * A simple [Fragment] subclass.
 * Use the [sip.newInstance] factory method to
 * create an instance of this fragment.
 */
class Compounding : Fragment() {
    private  val sipViewModel by activityViewModels<CompoundingViewModel>()
    private var _binding: FragmentSipBinding? = null
    private val binding get() = _binding!!
    private var pieChart: PieChart? = null
    private var lineChart:LineChart? = null
    private var interval: Long=0;




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSipBinding.inflate(inflater, container, false).apply {
            this.sip=sipViewModel
        }
        val root: View = binding.root
        lineChart = binding.lineChart
        binding.calculateButton.setOnClickListener {
            run {
                hideKeyboard()
                sipViewModel.calculateReturns()
                //showPieChart(sipViewModel)
                setDataToLineChart()
                setUpLineChart(lineChart!!)



            }
        }
        setUpLineChart(lineChart!!)

        return root    }

    fun showPieChart(sipViewModel: CompoundingViewModel) {
        val pieEntries = ArrayList<PieEntry>()


        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()

        typeAmountMap["Investment Amount"] = sipViewModel.compounding.compoundedAmount.get().toInt()
        typeAmountMap["Interest"] = sipViewModel.compounding.compoundedAmount.get().toInt()-
                sipViewModel.compounding.investmentAmount.toInt()

        //initializing colors for the entries
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#C52F51"))
        colors.add(Color.parseColor("#1FBFBE"))

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))

        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, "")

        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)
        pieChart?.data=(pieData)
        pieChart?.holeRadius=45f
        pieChart?.textAlignment= View.TEXT_ALIGNMENT_CENTER
        var description= Description()
        description.isEnabled=false
        pieChart?.description= description
        pieChart?.invalidate()

    }

    private fun setUpLineChart(lineChart: LineChart) {
        with(lineChart) {
            animateX(1200, Easing.EaseInSine)
            description.isEnabled = false

            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F
            xAxis.valueFormatter = MyAxisFormatter()

            axisRight.isEnabled = false
            extraRightOffset = 30f

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 15F
            legend.form = Legend.LegendForm.LINE
        }
    }
    private fun setDataToLineChart() {
        interval=sipViewModel.compounding.years/4;

        var entry1=sipViewModel.compounding.copy(years = 1,compoundedAmount = ObservableDouble());
          sipViewModel.computeReturns(entry1)
        var entry2=sipViewModel.compounding.copy(years = interval*1,compoundedAmount = ObservableDouble());
                  sipViewModel.computeReturns(entry2)
        var entry3=sipViewModel.compounding.copy(years = interval*2, compoundedAmount = ObservableDouble());
                   sipViewModel.computeReturns(entry3)
        var entry4=sipViewModel.compounding.copy(years = interval*3,compoundedAmount = ObservableDouble())
                   sipViewModel.computeReturns(entry4)
        var entry5=sipViewModel.compounding.copy(compoundedAmount = ObservableDouble());
                   sipViewModel.computeReturns(entry5)




        val compoundedAmounts = LineDataSet(compoundedAmount(entry1,entry2,entry3,entry4,entry5), "Compounded Amount")
        compoundedAmounts.lineWidth = 3f
        compoundedAmounts.valueTextSize = 15f
        compoundedAmounts.mode = LineDataSet.Mode.CUBIC_BEZIER
        compoundedAmounts.color = ContextCompat.getColor(this.requireContext(), R.color.green)
        compoundedAmounts.valueTextColor = ContextCompat.getColor(this.requireContext(), R.color.black)
        compoundedAmounts.enableDashedLine(20F, 10F, 0F)

        val investedAmounts = LineDataSet(investedAmount(entry1,entry2,entry3,entry4,entry5), "Amount Invested")
        investedAmounts.lineWidth = 3f
        investedAmounts.valueTextSize = 15f
        investedAmounts.mode = LineDataSet.Mode.CUBIC_BEZIER
        investedAmounts.color = ContextCompat.getColor(this.requireContext(), R.color.yellow)
        investedAmounts.valueTextColor = ContextCompat.getColor(this.requireContext(), R.color.black)
        investedAmounts.enableDashedLine(20F, 10F, 0F)


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(compoundedAmounts)
        dataSet.add(investedAmounts)

        val lineData = LineData(dataSet)
        lineChart?.data = lineData

        lineChart?.invalidate()
    }

    fun Fragment.hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
    private fun investedAmount(entry1:COMPOUNDING,entry2:COMPOUNDING,entry3:COMPOUNDING,entry4:COMPOUNDING,entry5:COMPOUNDING): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, entry1.investmentAmount.toFloat()))
        sales.add(Entry(1f, entry2.investmentAmount.toFloat()))
        sales.add(Entry(2f, entry3.investmentAmount.toFloat()))
        sales.add(Entry(3f, entry4.investmentAmount.toFloat()))
        sales.add(Entry(4f, entry5.investmentAmount.toFloat()))
        return sales
    }

    private fun compoundedAmount(entry1:COMPOUNDING,
                                 entry2:COMPOUNDING,entry3:COMPOUNDING,
                                 entry4:COMPOUNDING,entry5:COMPOUNDING): ArrayList<Entry> {
        var intervals=sipViewModel.compounding.years/4;


        val sales = ArrayList<Entry>()
   sales.add(Entry(0f, entry1.compoundedAmount.get().toFloat()))
   sales.add(Entry(1f, entry2.compoundedAmount.get().toFloat()))
   sales.add(Entry(2f, entry3.compoundedAmount.get().toFloat()))
   sales.add(Entry(3f, entry4.compoundedAmount.get().toFloat()))
   sales.add(Entry(4f, entry5.compoundedAmount.get().toFloat()))
        return sales
    }
    inner class MyAxisFormatter : IndexAxisValueFormatter() {


        private var items = arrayListOf("0Year", (interval*1).toString()+"Year",
            (interval*2).toString()+"Year", (interval
                    *3).toString()+"Year", (sipViewModel.compounding.years).toString()+"Year")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < items.size) {
                items[index]
            } else {
                null
            }
        }
    }
}

