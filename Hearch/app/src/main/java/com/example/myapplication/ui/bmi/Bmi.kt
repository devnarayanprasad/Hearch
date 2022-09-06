package com.example.myapplication.ui.bmi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentBmiBinding


class Bmi : Fragment() {

    private  val bmiViewModel by activityViewModels<BmiViewModel>()
    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBmiBinding.inflate(inflater, container, false).apply {

            this.bmi=bmiViewModel
        }
        val root: View = binding.root

        binding.calculateBMI.setOnClickListener {
            run {
                bmiViewModel.setBMI();

            }
        }

        return root
    }


}