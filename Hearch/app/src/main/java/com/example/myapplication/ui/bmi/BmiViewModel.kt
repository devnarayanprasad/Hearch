package com.example.myapplication.ui.bmi

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableLong
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.BMI
import java.math.BigDecimal
import java.math.RoundingMode

class BmiViewModel : ViewModel() {
    val bmi=BMI(ObservableLong(0), ObservableLong(0),
        ObservableLong(0), ObservableDouble(0.0))
    private val CENTIMETERS_IN_METER: BigDecimal = BigDecimal("100.0");
    fun setBMI()
    {
        if(bmi.height.get()>0 && bmi.weight.get()>0 && bmi.age.get()>0)
        {
            var bmiNumber=(bmi.weight.get() /
                    (BigDecimal(bmi.height.get()).divide(CENTIMETERS_IN_METER)).multiply(BigDecimal(bmi.height.get()).divide(CENTIMETERS_IN_METER))
                        .setScale(0, RoundingMode.HALF_UP).toDouble())
            bmi.bmi.set(bmiNumber)

        }


    }


}