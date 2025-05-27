package com.example.dndbuilder.fragments

import UserPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dndbuilder.R
import com.example.dndbuilder.character.CharacterAdapter
import com.example.dndbuilder.character.CharacterItem
import com.example.dndbuilder.character.CharacterViewModel
import com.example.dndbuilder.databinding.FragmentMainBinding
import com.example.dndbuilder.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class FragmentProfile : Fragment(R.layout.fragment_profile){
    private var binding: FragmentProfileBinding? = null
    private lateinit var viewModel: CharacterViewModel
    private lateinit var adapter: CharacterAdapter
    private lateinit var userPrefs: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
        adapter = CharacterAdapter()
        userPrefs = UserPreferences(requireContext())
        viewModel.loadAllCharacters()
        binding?.goHome?.setOnClickListener{
            findNavController().navigate(FragmentProfileDirections.actionFragmentProfileToFragmentMain())
        }
        binding?.signOut?.setOnClickListener {
            lifecycleScope.launch {
                userPrefs.setSignedIn(false)
                findNavController().navigate(FragmentProfileDirections.actionFragmentProfileToFragmentLogin())
            }
        }
        adapter.onCharacterClickListener = { characterItem ->
            val action = FragmentProfileDirections.actionFragmentProfileToFragmentShowCharacter(characterItem)
            findNavController().navigate(action)
        }

        binding?.recyclerCharacters?.adapter = adapter
        binding?.recyclerCharacters?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            val adapterItems = characters.map { characterResponse ->

                fun getAbilityScore(abilityName: String): Int? {
                    val ability = characterResponse.abilities.find { it.ability.equals(abilityName, ignoreCase = true) }
                    return ability?.baseScore?.let { base ->
                        base + (ability.backgroundModifier ?: 0)
                    }
                }
                fun getImageResId(characterClass: String): Int {
                    return when (characterClass.lowercase()) {
                        "fighter" -> R.drawable.fighter_image
                        "druid" -> R.drawable.druid
                        "bard" -> R.drawable.bard_image
                        "cleric" -> R.drawable.cleric_image
                        "barbarian" -> R.drawable.barbarian_image
                        "monk" -> R.drawable.monk_image
                        "paladin" -> R.drawable.paladin_image
                        "sorcerer" -> R.drawable.sorcerer_image
                        "rogue" -> R.drawable.rogue_image
                        "warlock" -> R.drawable.warlock_image
                        "ranger" -> R.drawable.ranger_image
                        else -> R.drawable.default_character_image
                    }
                }

                CharacterItem(
                    name = characterResponse.name,
                    characterClass = characterResponse.characterClass,
                    specie = characterResponse.specie,
                    background = characterResponse.background,
                    str = getAbilityScore("STRENGTH"),
                    dex = getAbilityScore("DEXTERITY"),
                    con = getAbilityScore("CONSTITUTION"),
                    intelligence = getAbilityScore("INTELLIGENCE"),
                    wisdom = getAbilityScore("WISDOM"),
                    charisma = getAbilityScore("CHARISMA"),
                    id = characterResponse.id,
                    imageResId = getImageResId(characterResponse.characterClass)
                )
            }
            adapter.submitList(adapterItems)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

