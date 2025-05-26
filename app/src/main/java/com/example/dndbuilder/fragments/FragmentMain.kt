package com.example.dndbuilder.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.R
import com.example.dndbuilder.databinding.FragmentMainBinding

class FragmentMain :Fragment(R.layout.fragment_main){
    private var binding : FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding?.saveCharacterButton?.setOnClickListener {
            findNavController().navigate(FragmentMainDirections.actionMainToCharacter())
        }
    }

}