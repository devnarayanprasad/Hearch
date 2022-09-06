package com.example.myapplication.ui.retirement

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.RETIREMENT
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class RetirementViewModel :ViewModel() {

    val retire:RETIREMENT=RETIREMENT(0, 0,
    0, 0.0, 0.0, 0.0,ObservableDouble(0.0)
    );


    fun calculateRetirement()
    {
(
       retire.sipMonth.set(pmt(BigDecimal(retire.expectedReturn),
           (retire.lifeExpectancy -retire.retirementAge)*12,
            BigDecimal(retire.CurrentExpense.toInt()),false).toDouble()))
        
    }
    fun pmt(rate: BigDecimal, months: Int, presentValue: BigDecimal, t: Boolean): BigDecimal {
        var result: BigDecimal = if (rate.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal("-1.0").multiply(presentValue)
                .divide(BigDecimal(months), RoundingMode.HALF_UP)
        } else {
            val r1: BigDecimal = rate.add(BigDecimal.ONE)
            val opt: BigDecimal = if (t) r1 else BigDecimal.ONE
            presentValue.multiply(r1.pow(months)).multiply(rate)
                .divide(opt.multiply(BigDecimal.ONE.subtract(r1.pow(months))), RoundingMode.HALF_UP)
        }
        return result
    }



}