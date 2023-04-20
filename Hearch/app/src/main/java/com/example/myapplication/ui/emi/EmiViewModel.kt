package com.example.myapplication.ui.emi

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableLong
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.EMI
import kotlin.math.roundToLong

class EmiViewModel : ViewModel() {

    val emi: EMI = EMI(
        0.0, 0.0, 0,
        0.0, ObservableLong(0), 0.0, ObservableLong(0)
    )
    var loadValues: Boolean = false;

    fun setEmiAmount() {
        if ((emi.loanAmount > 0 && emi.loanRateOfInterest > 0 && emi.tenureMonths > 0)
        ) {
            val rateOfInterest = emi.loanRateOfInterest / (12 * 100);
            val monthlyEmi: Double =
                (emi.loanAmount * rateOfInterest * Math.pow(
                    1 + rateOfInterest,
                    emi.tenureMonths.toDouble()
                )/ (Math.pow(1 + rateOfInterest, emi.tenureMonths.toDouble()) - 1))
            emi.interestComponent = (monthlyEmi * emi.tenureMonths) - emi.loanAmount
            emi.emiAmount.set(monthlyEmi.roundToLong())
            emi.totalAmount.set((emi.interestComponent+emi.loanAmount).toLong());
        } else {
            emi.emiAmount.set(0)
        }
        loadValues = true
    }
}