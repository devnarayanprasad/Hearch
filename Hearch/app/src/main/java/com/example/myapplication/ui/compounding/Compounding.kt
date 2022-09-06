package com.example.myapplication.ui.compounding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentSipBinding


/**
 * A simple [Fragment] subclass.
 * Use the [sip.newInstance] factory method to
 * create an instance of this fragment.
 */
class Compounding : Fragment() {
    private  val sipViewModel by activityViewModels<CompoundingViewModel>()
    private var _binding: FragmentSipBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSipBinding.inflate(inflater, container, false).apply {
            this.sip=sipViewModel
        }
        val root: View = binding.root

        binding.calculateButton.setOnClickListener {
            run {
                hideKeyboard()
                sipViewModel.calculateReturns()

            }
        }

        return root    }
    fun Fragment.hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}