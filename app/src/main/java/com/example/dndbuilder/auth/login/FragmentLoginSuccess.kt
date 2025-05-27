package com.example.dndbuilder.auth.login

import UserPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.R
import com.example.dndbuilder.databinding.FragmentLoginSuccessBinding
import kotlinx.coroutines.launch

class FragmentLoginSuccess :Fragment(R.layout.fragment_login_success){
    private var binding : FragmentLoginSuccessBinding? = null
    private lateinit var userPrefs: UserPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginSuccessBinding.bind(view)
        userPrefs = UserPreferences(requireContext())
        binding?.loginButton?.setOnClickListener {
            findNavController().navigate(FragmentLoginSuccessDirections.actionRegSecToMain())
            lifecycleScope.launch {
                userPrefs.setSignedIn(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}