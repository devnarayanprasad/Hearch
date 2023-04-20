package com.example.myapplication.ui.bmi

import androidx.databinding.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.BMI
import java.math.BigDecimal
import java.math.RoundingMode

class BmiViewModel : ViewModel() {
    val bmi=BMI(0.0, 0.0,  0,0,0,ObservableDouble(0.0), ObservableBoolean(true),
        ObservableField<String>(BMICategory.Normal.name),ObservableInt(BMICategory.Normal.color))
    private val METER_IN_CM: BigDecimal = BigDecimal("100.0");

    var loadValues : Boolean=false;

    fun setBMI()
    {
        if(!bmi.isMetric.get())
        {

          bmi.height=(bmi.heightFeetUS*30.48)+(bmi.heightInchUS*2.54);
          bmi.weight=bmi.weightUS*0.45359237;
        }

        if(bmi.height>0 && bmi.weight>0)
        {
            var bmiNumber=BigDecimal(bmi.weight).divide(
                ((BigDecimal(bmi.height).divide(METER_IN_CM)).multiply(BigDecimal(bmi.height).divide(METER_IN_CM))
                        ),1, RoundingMode.HALF_UP).toDouble()
            bmi.bmi.set(bmiNumber)
            if(bmiNumber<18.5) {
                bmi.category.set(BMICategory.Underweight.name)
                bmi.color.set(BMICategory.Underweight.color)
            }
            else if(bmiNumber<=24.9)
            {
                bmi.category.set(BMICategory.Normal.name)
                bmi.color.set(BMICategory.Normal.color)
            }
            else if(bmiNumber<=29.9)
            {
                bmi.category.set(BMICategory.Overweight.name)
                bmi.color.set(BMICategory.Overweight.color)
            }
            else if(bmiNumber>=30.0)
            {
                bmi.category.set(BMICategory.Obese.name)
                bmi.color.set(BMICategory.Obese.color)
            }
            loadValues=true;

        }


    }


}