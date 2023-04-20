package com.example.myapplication.model

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableLong

data class EMI(var loanAmount :Double,
               var loanRateOfInterest:Double,
               var tenureMonths:Long,
               var preProcessingFeeRate:Double,
               var emiAmount: ObservableLong,
               var interestComponent:Double,
               var totalAmount:ObservableLong
)