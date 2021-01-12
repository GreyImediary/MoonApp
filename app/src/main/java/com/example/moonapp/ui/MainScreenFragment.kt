package com.example.moonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moonapp.*
import com.example.moonapp.constants.PREF_IS_PAID
import com.example.moonapp.data.Result
import com.example.moonapp.databinding.MainScreenFragmentBinding
import com.example.moonapp.viewModels.MainScreenViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

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

        getMoonDay()

        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }

        binding.root.setOnRefreshListener {
            getMoonDay()
        }

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_MainScreen_to_settingsFragment)
        }

        setOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        setMeditationIcons()
    }

    private fun setMeditationIcons() {
        val isPaid = context?.prefs?.getBoolean(PREF_IS_PAID, false) ?: false

        if (isPaid) {
            binding.meditation1.visible()
            binding.meditation2.visible()

            binding.meditation1Lock.invisible()
            binding.meditation2Lock.invisible()
        } else {
            binding.meditation1.invisible()
            binding.meditation2.invisible()

            binding.meditation1Lock.visible()
            binding.meditation2Lock.visible()
        }
    }

    private fun getMoonDay() {
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
        binding.timeText.text = DateUtils.getTime()
        binding.cityText.text = TimeZone.getDefault()
            .getDisplayName(Locale("ru"))
            .split(',')[0]

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
        binding.root.isRefreshing = false
    }

    private fun setOnClickListener() {
        binding.meditation1Lock.setOnClickListener {
            showLockSnackBar()
        }

        binding.meditation2Lock.setOnClickListener {
            showLockSnackBar()
        }
    }

    private fun showLockSnackBar() {
        Snackbar
            .make(binding.root, R.string.lock_text, Snackbar.LENGTH_SHORT)
            .setAction("OK") {
                findNavController().navigate(R.id.action_MainScreen_to_SubscriptionFragment)
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}