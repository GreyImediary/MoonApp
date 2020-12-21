package com.example.moonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moonapp.R
import com.example.moonapp.constants.*
import com.example.moonapp.databinding.FragmentSignInBinding
import com.example.moonapp.gone
import com.example.moonapp.prefs
import com.google.android.material.snackbar.Snackbar

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
                context?.prefs?.edit {
                    putString(PREF_USER_NAME, binding.editUserName.text.toString())
                    putString(PREF_USER_PASS, binding.editUserPass.text.toString())
                }

                findNavController().navigate(R.id.action_SignInFragment_to_MainScreen)
            }
        }

        binding.buttonSignIn.setOnClickListener {
            if (isPaidLogIn()) {
                context?.prefs?.edit {
                    putString(PREF_USER_NAME, paidLogin)
                    putString(PREF_USER_PASS, paidPass)
                    putBoolean(PREF_IS_PAID, true)
                }

                findNavController().navigate(R.id.action_SignInFragment_to_MainScreen)
                return@setOnClickListener
            }

            if (checkInputs()) {
                if (checkLogIn()) {
                    findNavController().navigate(R.id.action_SignInFragment_to_MainScreen)
                }
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
        } else {
            binding.inputUserName.error = null
        }

        if (pass.isEmpty()) {
            binding.inputUserPass.error = getString(R.string.user_pass_error)
        } else {
            binding.inputUserPass.error = null
        }

        return false
    }

    private fun isPaidLogIn(): Boolean {
        val name = binding.editUserName.text.toString()
        val pass = binding.editUserPass.text.toString()

        return name == paidLogin && pass == paidPass
    }

    private fun checkLogIn(): Boolean {
        val prefName = context?.prefs?.getString(PREF_USER_NAME, "") ?: ""
        val prefPass = context?.prefs?.getString(PREF_USER_PASS, "") ?: ""

        val editName = binding.editUserName.text.toString()
        val editPass = binding.editUserPass.text.toString()

        if (prefName == editName && prefPass == editPass) {
            return true
        }

        Snackbar
            .make(binding.root, R.string.log_in_snackbar, Snackbar.LENGTH_SHORT)
            .show()

        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}