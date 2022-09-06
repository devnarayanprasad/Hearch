package com.example.myapplication.ui.emi

import androidx.databinding.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.EMI
import kotlin.math.roundToLong

class EmiViewModel : ViewModel() {

    val emi: EMI = EMI(0.0,0.0,0,
        0.0,ObservableDouble(0.0),0.0
    )


    fun setEmiAmount()
    {
        if((emi.loanAmount>0 && emi.loanRateOfInterest>0 && emi.tenureMonths>0)
            )
        {

            val rateOfInterest= emi.loanRateOfInterest / (12 * 100);


            val monthlyEmi: Double =


            (emi.loanAmount * rateOfInterest* Math.pow(1 + rateOfInterest, emi.tenureMonths.toDouble()).toFloat()
                    / (Math.pow(1 + rateOfInterest, emi.tenureMonths.toDouble()) - 1).toFloat())


            emi.emiAmount.set(monthlyEmi.roundToLong().toDouble())
            emi.interestComponent=(monthlyEmi*emi.tenureMonths-emi.loanAmount);
        }
        else
        {
            emi.emiAmount.set(0.0)

        }
    }
}