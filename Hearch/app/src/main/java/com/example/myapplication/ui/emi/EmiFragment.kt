package com.example.myapplication.ui.emi

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentEmiBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF





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
                emiViewModel.setEmiAmount()
                hideKeyboard()
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


        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()

        typeAmountMap["Principal"] = emiViewModel.emi.loanAmount.toInt()
        typeAmountMap["Interest"] = emiViewModel.emi.interestComponent.toInt()

        //initializing colors for the entries
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#FF0000"))
         colors.add(Color.parseColor("#47B39C"))

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))

        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, "")

        //setting text size of the value
        pieDataSet.valueTextSize = 14f



         //providing color list for coloring different entries
        pieDataSet.colors = colors
         pieDataSet.valueTextColor=Color.BLACK
         pieDataSet.color
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)
        pieChart?.data=(pieData)
         pieChart?.holeRadius=50f
         pieChart?.textAlignment= View.TEXT_ALIGNMENT_CENTER
         pieChart?.setExtraOffsets(5f, 5f, 5f, 5f)
         pieChart?.setEntryLabelColor(Color.BLACK)
         pieChart?.setEntryLabelTextSize(14f)
         pieChart?.setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
        // pieChart?.setUsePercentValues(true)
         pieChart?.legend?.isEnabled = false
         pieChart?.description?.isEnabled = false
         pieChart?.isRotationEnabled = true
         pieChart?.dragDecelerationFrictionCoef = 0.9f
         pieChart?.rotationAngle = 220f
         pieChart?.isHighlightPerTapEnabled = true
         pieChart?.animateY(1400, Easing.EaseInOutQuad)
         pieChart?.setHoleColor(Color.WHITE)

         var description=Description()
         description.isEnabled=false
         pieChart?.description= description
         pieChart?.invalidate()

    }
    fun Fragment.hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}