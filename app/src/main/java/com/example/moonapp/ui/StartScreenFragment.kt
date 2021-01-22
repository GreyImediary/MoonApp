package com.example.moonapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moonapp.R
import com.example.moonapp.constants.PREF_USER_NAME
import com.example.moonapp.databinding.FragmentStartScreenBinding
import com.example.moonapp.gone
import com.example.moonapp.prefs

/**
 * Фрагмент, который является начальным экраном для незарегистрированного пользователя.
 * Через него пользователь переходит на экраны регистрации и авторизации.
* */
class StartScreenFragment : Fragment() {

    private var _binding: FragmentStartScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkUserLoggedIn()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as MainActivity).binding.toolbar.gone()

        setOnClickListeners()
        setSlider()
    }

    private fun checkUserLoggedIn(): Boolean {
        val name = context?.prefs?.getString(PREF_USER_NAME, "") ?: ""

        if (name.isNotBlank()) {
            findNavController().navigate(R.id.action_StartScreenFragment_to_MainScreen)
        }

        return false
    }

    private fun setOnClickListeners() {
        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_StartScreenFragment_to_SignInFragment)
        }
    }

    private fun setSlider() {
        val sliderTexts = resources.getStringArray(R.array.slider_texts).toList()
        binding.slider.apply {
            setSliderAdapter(SliderAdapter(sliderTexts))
            startAutoCycle()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}