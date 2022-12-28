package com.fintold.athletesapp.uI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fintold.athletesapp.R
import com.fintold.athletesapp.databinding.FragmentAthleteDetailsBinding

class AthleteDetailsFragment : Fragment() {
    private var binding: FragmentAthleteDetailsBinding ?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_athlete_details,container,false)
        binding?.lifecycleOwner = viewLifecycleOwner

        val athlete = AthleteDetailsFragmentArgs.fromBundle(requireArguments()).athleteSelected
        binding?.athleteSelected = athlete

        setFragmentTitle(athlete.name)


        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setFragmentTitle(athleteName: String) {
        (requireActivity() as MainActivity).setActionBarTitle(athleteName)
    }

}