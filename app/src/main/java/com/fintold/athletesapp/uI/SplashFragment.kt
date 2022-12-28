package com.fintold.athletesapp.uI

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.fintold.athletesapp.R
import com.fintold.athletesapp.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SplashFragment : Fragment() {
    private var binding: FragmentSplashBinding ?=null
    private val viewModel by sharedViewModel<AthletesViewModel>()
    private lateinit var dialog: AlertDialog
    private val counter = object: CountDownTimer(2000,1000) {
        override fun onTick(p0: Long) {}
        override fun onFinish() {
            viewModel.showAlert.observe(viewLifecycleOwner) {
                if(it)
                    showDialog()
                else {
                    dismissDialog()
                    navigateToAthletesListScreen()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash,container,false)
        (requireActivity() as MainActivity).supportActionBar?.hide()

        createAlertDialog()

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        counter.start()
        viewModel.getAthletes()
    }

    override fun onStop() {
        super.onStop()
        counter.cancel()
    }

    private fun navigateToAthletesListScreen() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAthletesListFragment())
    }

    private fun createAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_message)
        builder.setIcon(R.drawable.ic_wifi_tethering_error)
        builder.setPositiveButton(R.string.try_again) { it, _ ->
            viewModel.getAthletes()
            it.dismiss()
        }
        dialog = builder.create()
    }

    private val showDialog = { dialog.show() }
    private val dismissDialog = { dialog.dismiss() }

}