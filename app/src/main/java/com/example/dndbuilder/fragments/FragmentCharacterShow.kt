package com.example.dndbuilder.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dndbuilder.R
import com.example.dndbuilder.character.CharacterAdapter
import com.example.dndbuilder.character.CharacterItem
import com.example.dndbuilder.character.CharacterViewModel
import com.example.dndbuilder.databinding.FragmentProfileBinding
import com.example.dndbuilder.databinding.FragmentShowCharacterBinding


class FragmentCharacterShow : Fragment(R.layout.fragment_show_character) {
    private var binding: FragmentShowCharacterBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowCharacterBinding.bind(view)

        val characterItem: CharacterItem? = arguments?.let {
            FragmentCharacterShowArgs.fromBundle(it).characterItem
        }
        val imageId = characterItem?.imageResId ?: R.drawable.default_character_image
        binding?.imageCharacterShow?.setImageResource(imageId)
        characterItem?.let { character ->
            binding?.apply {
                textName.text = character.name
                textClassRace.text = character.characterClass
                abilityStrValue.text = character.str?.toString() ?: "-"
                abilityDexValue.text = character.dex?.toString() ?: "-"
                abilityConValue.text = character.con?.toString() ?: "-"
                abilityIntValue.text = character.intelligence?.toString() ?: "-"
                abilityWisValue.text = character.wisdom?.toString() ?: "-"
                abilityChaValue.text = character.charisma?.toString() ?: "-"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
