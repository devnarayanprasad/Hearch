package com.example.myapplication.model

import androidx.databinding.*
import com.example.myapplication.ui.bmi.BMICategory

data class BMI(var height:Double,
               var weight:Double,
               var weightUS:Long,
               var heightFeetUS:Long,
               var heightInchUS:Long,
               var bmi:ObservableDouble,
               var isMetric:ObservableBoolean= ObservableBoolean(true),
                var category:ObservableField<String>,
               var color:ObservableInt

)
