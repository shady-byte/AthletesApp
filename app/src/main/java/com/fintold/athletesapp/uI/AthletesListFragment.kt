package com.fintold.athletesapp.uI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.fintold.athletesapp.R
import com.fintold.athletesapp.adapters.OnClickListener
import com.fintold.athletesapp.adapters.RecyclerViewAdapter
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.databinding.FragmentAthletesListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AthletesListFragment : Fragment() {
    private val viewModel by sharedViewModel<AthletesViewModel>()
    private var binding: FragmentAthletesListBinding ?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_athletes_list,container,false)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding?.athletesRecyclerView?.adapter = RecyclerViewAdapter(OnClickListener {
            navigateToAthleteDetails(athlete = it)
        })
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun navigateToAthleteDetails(athlete: Athlete) {
        findNavController().navigate(AthletesListFragmentDirections.actionAthletesListFragmentToAthleteDetailsFragment(athlete))
    }

}