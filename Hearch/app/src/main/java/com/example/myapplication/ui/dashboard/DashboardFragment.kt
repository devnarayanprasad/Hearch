package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root




        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolsScreenEmi=view.findViewById<CardView>(R.id.tools_screen_emi);
        val toolsScreenBmi=view.findViewById<CardView>(R.id.tools_screen_bmi);
        //val toolsScreenFileShare=view?.findViewById<CardView>(R.id.tools_screen_fileshare);
        //val toolsScreenMemory=view?.findViewById<CardView>(R.id.tools_screen_memory);
        val toolsScreenRetire=view.findViewById<CardView>(R.id.tools_screen_retire);
        val toolsScreenSip=view.findViewById<CardView>(R.id.tools_screen_sip);



        toolsScreenEmi?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_dashboard_to_navigation_notifications, null))

        toolsScreenBmi?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_dashboard_to_bmi, null))

        //toolsScreenFileShare?.setOnClickListener(
        //    Navigation.createNavigateOnClickListener(R.id.action_navigation_dashboard_to_sip, null))
       // toolsScreenMemory?.setOnClickListener(
         //   Navigation.createNavigateOnClickListener(R.id.action_navigation_dashboard_to_retirement, null))
        toolsScreenRetire?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_dashboard_to_retirement, null))
        toolsScreenSip?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_dashboard_to_sip, null))

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}