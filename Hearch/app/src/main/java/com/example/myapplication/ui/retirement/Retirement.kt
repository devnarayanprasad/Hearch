package com.example.myapplication.ui.retirement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentRetirementBinding



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
                hideKeyboard()

            }
        }
        return root
    }
    fun Fragment.hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}