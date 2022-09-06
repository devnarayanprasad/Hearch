package com.example.myapplication.ui.retirement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentBmiBinding
import com.example.myapplication.databinding.FragmentRetirementBinding
import com.example.myapplication.ui.bmi.BmiViewModel
import com.example.myapplication.ui.retirement.RetirementViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [retirement.newInstance] factory method to
 * create an instance of this fragment.
 */
class retirement : Fragment() {

    private  val retirementViewModel by activityViewModels<RetirementViewModel>()
    private var _binding: FragmentRetirementBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRetirementBinding.inflate(inflater, container, false).apply {

            this.retirement = retirementViewModel
        }
        val root: View = binding.root

        binding.calculateButton.setOnClickListener {
            run {
                retirementViewModel.calculateRetirement()

            }
        }
        return root
    }


}