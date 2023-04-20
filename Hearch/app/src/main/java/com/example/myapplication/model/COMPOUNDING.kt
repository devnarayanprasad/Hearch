package com.example.myapplication.model

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableInt

data class COMPOUNDING(var initialAmount: Long,var monthlyAmount:Long,
                   var years:Long,var rateOfInterest:Double,
                       var compoundedAmount:ObservableDouble,var investmentAmount:Long){

}
