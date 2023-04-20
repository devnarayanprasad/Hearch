package com.example.myapplication.ui.utils

import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("stringToLong")
    @JvmStatic fun longToString(
        value: Long
    ): String {

        return if(value <= 0) {
            ""
        } else
            value.toString()

    }

    @JvmStatic fun stringToLong(
        value: String
    ): Long {
        // Converts String to long.
        return if(value.isEmpty()||value.isBlank()) {
            0
        } else {
            value.toLong()
        }
    }

    @InverseMethod("stringToDouble")
    @JvmStatic fun doubleToString(
        value: Double
    ): String {

        return if(value.compareTo(0.0)<=0) {
            ""
        } else
            value.toString()

    }

    @JvmStatic fun stringToDouble(
        value: String
    ): Double {
        // Converts String to long.
        return if(value.isEmpty()||value.isBlank()) {
            0.0
        } else {
            value.toDouble()
        }
    }
}