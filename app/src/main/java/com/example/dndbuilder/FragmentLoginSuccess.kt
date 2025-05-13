package com.example.dndbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.databinding.FragmentLoginSuccessBinding
import com.example.dndbuilder.databinding.FragmentSignUpBinding

class FragmentLoginSuccess :Fragment(R.layout.fragment_login_success){
    private var binding : FragmentLoginSuccessBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginSuccessBinding.bind(view)
        binding?.loginButton?.setOnClickListener {
            findNavController().navigate(FragmentLoginSuccessDirections.actionRegSecToMain())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}