package com.example.myapplication.ui.compounding

import androidx.databinding.ObservableDouble
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.COMPOUNDING
import java.math.BigDecimal
import java.math.RoundingMode

class CompoundingViewModel : ViewModel() {

    var compounding = COMPOUNDING(
        0,
        0,
            0, 0.0, ObservableDouble(0.0),0
    );

    var loadValues : Boolean=false;

    fun calculateReturns() {

        computeReturns(compounding);
    }

    fun computeReturns( compounding: COMPOUNDING)
    {
        if (compounding.years > 0 && compounding.rateOfInterest > 0 && (compounding.initialAmount > 0 ||
                    compounding.monthlyAmount > 0)
        ) {
            var monthlyRateOfInterest = BigDecimal(compounding.rateOfInterest).setScale(5, RoundingMode.HALF_UP)/BigDecimal("12")/BigDecimal("100")

            var sipReturns=BigDecimal(compounding.monthlyAmount).setScale(5, RoundingMode.HALF_UP)*
                    (((((BigDecimal(1).add(monthlyRateOfInterest)).pow(12)).subtract(
                        BigDecimal.ONE)).divide(monthlyRateOfInterest)).
                    multiply(BigDecimal.ONE.add(monthlyRateOfInterest)));

            var compoundedReturns=BigDecimal(compounding.initialAmount).setScale(5, RoundingMode.HALF_UP).
            multiply(BigDecimal.ONE.add((monthlyRateOfInterest).multiply(BigDecimal("12"))).pow(
                compounding.years.toInt()
            ))

            compounding.investmentAmount=sipReturns.toLong();
            compounding.compoundedAmount.set((sipReturns+compoundedReturns).toDouble());
            //compounding.investmentAmount=compounding.monthlyAmount*12*compounding.years;
            //compounding.compoundedAmount.set(
             //   compounding.monthlyAmount*12*compounding.years*compounding.rateOfInterest);

            loadValues=true

        }

    }
}

