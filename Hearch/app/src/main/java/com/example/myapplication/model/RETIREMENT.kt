package com.example.myapplication.model

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableInt
import java.time.Month

data class RETIREMENT(var ageNow:Long,var retirementAge: Long,var lifeExpectancy:Long,
var currentCorpus:Long,var CurrentExpense:Long,var expectedReturn:Double,var sipMonth: ObservableDouble)
