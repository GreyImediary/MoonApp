package com.example.moonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moonapp.R
import com.example.moonapp.databinding.FragmentSignInBinding
import com.example.moonapp.gone

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as MainActivity).binding.toolbar.gone()

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.buttonSignUp.setOnClickListener {
            if (checkInputs()) {

            }
        }

        binding.buttonSignIn.setOnClickListener {
            if (checkInputs()) {

            }
        }
    }

    private fun checkInputs(): Boolean {
        val userName = binding.editUserName.text.toString()
        val pass = binding.editUserPass.text.toString()

        if (userName.isNotEmpty() && pass.isNotEmpty()) {
            return true
        }

        if (userName.isEmpty()) {
            binding.inputUserName.error = getString(R.string.user_name_error)
        }

        if (pass.isEmpty()) {
            binding.inputUserPass.error = getString(R.string.user_pass_error)
        }

        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}