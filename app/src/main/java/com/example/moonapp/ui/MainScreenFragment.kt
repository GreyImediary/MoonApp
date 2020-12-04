package com.example.moonapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moonapp.DateUtils
import com.example.moonapp.R
import com.example.moonapp.data.MoonDayData
import com.example.moonapp.data.Result
import com.example.moonapp.databinding.FragmentSignInBinding
import com.example.moonapp.databinding.MainScreenFragmentBinding
import com.example.moonapp.gone
import com.example.moonapp.viewModels.MainScreenViewModel
import com.example.moonapp.visible
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModel()

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }

        viewModel.getMoonPhase().observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    setMoonDayContent(it.data)
                }

                is Result.Failure -> {
                    hideProgress()
                    hideContent()
                    Snackbar
                        .make(binding.root, R.string.moon_day_error, Snackbar.LENGTH_LONG)
                        .show()
                }
                Result.Loading -> {
                    hideContent()
                    showProgress()
                }
            }
        }
    }

    private fun setMoonDayContent(moonDay: Int) {
        binding.moonDay.text = moonDay.toString()
        binding.moonDayText.text = resources.getStringArray(R.array.moon_day_texts)[moonDay - 1]
        binding.dateText.text = DateUtils.getFullDay()

        showContent()
        hideProgress()
    }

    private fun showContent() {
        binding.contentContainer.visible()
    }

    private fun hideContent() {
        binding.contentContainer.gone()
    }

    private fun showProgress() {
        binding.progressBar.visible()
    }

    private fun hideProgress() {
        binding.progressBar.gone()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}