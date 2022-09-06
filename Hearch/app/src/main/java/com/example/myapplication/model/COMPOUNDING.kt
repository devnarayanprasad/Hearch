package com.example.myapplication.model

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableInt

data class COMPOUNDING(var initialAmount: Double,var monthlyAmount:Double,
                   var months:Int,var rateOfInterest:Double,var compoundedAmount:ObservableDouble){

}
