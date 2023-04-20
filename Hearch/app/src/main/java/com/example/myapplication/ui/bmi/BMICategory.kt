package com.example.myapplication.ui.bmi

import android.graphics.Color
import android.provider.CalendarContract
import androidx.core.graphics.toColor

enum class BMICategory(val color:Int)
{
    Underweight(Color.rgb(236, 158, 23)),
    Normal(Color.rgb(45, 194, 34)),
    Overweight(Color.rgb(255, 215, 55)),
    Obese(Color.rgb(236, 74, 23));
}
