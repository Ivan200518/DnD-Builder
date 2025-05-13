package com.example.dndbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.databinding.FragmentCharactericticsBinding
import com.example.dndbuilder.databinding.FragmentLoginSuccessBinding
import com.example.dndbuilder.databinding.FragmentMainBinding
import com.example.dndbuilder.databinding.FragmentSignUpBinding

class FragmentMain :Fragment(R.layout.fragment_main){
    private var binding : FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding?.button?.setOnClickListener {
            findNavController().navigate(FragmentMainDirections.actionMainToCharacter())
        }
    }

}