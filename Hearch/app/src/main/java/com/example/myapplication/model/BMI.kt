package com.example.myapplication.model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableLong

data class BMI(var height:ObservableLong,
               var weight:ObservableLong,
               var age:ObservableLong,
               var bmi:ObservableDouble)
