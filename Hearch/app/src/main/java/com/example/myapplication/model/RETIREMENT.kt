package com.example.myapplication.model

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableInt
import java.time.Month

data class RETIREMENT(var ageNow:Int,var retirementAge: Int,var lifeExpectancy:Int,
var currentCorpus:Double,var CurrentExpense:Double,var expectedReturn:Double,var sipMonth: ObservableDouble)
