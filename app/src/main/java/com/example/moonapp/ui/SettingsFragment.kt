package com.example.moonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moonapp.R
import com.example.moonapp.databinding.FragmentSettingsBinding
import com.example.moonapp.prefs

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.logOutButton.setOnClickListener {
            context?.prefs?.edit {
                clear()
            }

            findNavController().popBackStack(R.id.StartScreenFragment, false)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}