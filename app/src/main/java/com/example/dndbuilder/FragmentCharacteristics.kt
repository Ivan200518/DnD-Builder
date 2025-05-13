package com.example.dndbuilder


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.databinding.FragmentCharactericticsBinding
import com.example.dndbuilder.databinding.FragmentLoginSuccessBinding
import com.example.dndbuilder.databinding.FragmentSignUpBinding

class FragmentCharacteristics :Fragment(R.layout.fragment_characterictics){
    private var binding :FragmentCharactericticsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactericticsBinding.bind(view)

        childFragmentManager.beginTransaction()
            .replace(R.id.subfragment_container, RaceFragment())
            .commit()

        view.findViewById<ImageButton>(R.id.icon_race).setOnClickListener {
            swapSubFragment(RaceFragment())
        }



        view.findViewById<ImageButton>(R.id.icon_class).setOnClickListener {
            swapSubFragment(ClassFragment())
        }

        view.findViewById<ImageButton>(R.id.icon_background).setOnClickListener {
            swapSubFragment(FragmentBack())
        }

        view.findViewById<ImageButton>(R.id.icon_states).setOnClickListener {
            swapSubFragment(StatsTableFragment())
        }


    }

    private fun swapSubFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.subfragment_container, fragment)
            .commit()
    }
    
}