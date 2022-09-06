package com.example.myapplication.ui.compounding

import androidx.databinding.ObservableDouble
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.COMPOUNDING
import java.math.BigDecimal
import java.math.RoundingMode

class CompoundingViewModel : ViewModel() {

    var compounding = COMPOUNDING(
        0.0,
        0.0,
            0, 0.0, ObservableDouble(0.0)
    );

    fun calculateReturns() {

        if (compounding.months > 0 && compounding.rateOfInterest > 0 && (compounding.initialAmount > 0 ||
                    compounding.monthlyAmount > 0)
        ) {
            var monthlyRateOfInterest =
                BigDecimal(compounding.rateOfInterest).divide(BigDecimal("12"))
                    .divide(BigDecimal("100"));

            var sipReturns=(compounding.monthlyAmount)*
                    (((((BigDecimal(1).add(monthlyRateOfInterest)).pow(12)).subtract(
                BigDecimal.ONE)).divide(monthlyRateOfInterest)).
                    multiply(BigDecimal.ONE.add(monthlyRateOfInterest))).setScale(0, RoundingMode.HALF_UP).toDouble();

            var compoundedReturns=BigDecimal(compounding.initialAmount).
            multiply(BigDecimal.ONE.add((monthlyRateOfInterest).multiply(BigDecimal("12"))).pow(
               12*compounding.months
            )).setScale(0, RoundingMode.HALF_UP).toDouble();

            compounding.compoundedAmount.set(sipReturns+compoundedReturns);

        }
    }
}

