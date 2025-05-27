package com.example.dndbuilder.fragments


import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.dndbuilder.R
import com.example.dndbuilder.character.AbilityRequest
import com.example.dndbuilder.character.CharacterApi
import com.example.dndbuilder.character.CharacterRequest
import com.example.dndbuilder.character.CharacterViewModel
import com.example.dndbuilder.character.ClassFragment
import com.example.dndbuilder.databinding.FragmentCharactericticsBinding
import com.example.dndbuilder.stats.StatsTableFragment
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentCharacteristics :Fragment(R.layout.fragment_characterictics){
    private var binding :FragmentCharactericticsBinding? = null
    private lateinit var retrofit : Retrofit
    private val viewModel: CharacterViewModel by activityViewModels()
    private lateinit var api: CharacterApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactericticsBinding.bind(view)

        childFragmentManager.beginTransaction()
            .replace(R.id.subfragment_container, RaceFragment())
            .commit()


        setupRetrofit()

        binding?.button?.setOnClickListener {
            val input = EditText(requireContext()).apply {
                hint = "Enter character name"
                setPadding(40, 40, 40, 40)
                inputType = InputType.TYPE_CLASS_TEXT
                setTextColor(Color.BLACK)
            }

            AlertDialog.Builder(requireContext())
                .setTitle("Character Name")
                .setMessage("Please enter the name of your character:")
                .setView(input)
                .setPositiveButton("Save") { dialog, _ ->
                    val name = input.text.toString().trim()
                    if (name.isNotEmpty()) {
                        viewModel.name.value = name
                        viewModel.saveAbilitiesFromStats(viewModel.stats.value)
                        sendCharacterToBackend()
                    } else {
                        Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }

        val icons = listOf(
            view.findViewById<ImageButton>(R.id.icon_race),
            view.findViewById<ImageButton>(R.id.icon_class),
            view.findViewById<ImageButton>(R.id.icon_background),
            view.findViewById<ImageButton>(R.id.icon_states)
        )

        icons[0].setOnClickListener {
            setSelectedIcon(icons[0], icons)
            swapSubFragment(RaceFragment())
        }

        icons[1].setOnClickListener {
            setSelectedIcon(icons[1], icons)
            swapSubFragment(ClassFragment())
        }

        icons[2].setOnClickListener {
            setSelectedIcon(icons[2], icons)
            swapSubFragment(FragmentBack())
        }

        icons[3].setOnClickListener {
            setSelectedIcon(icons[3], icons)
            swapSubFragment(StatsTableFragment())
        }



    }
    private fun setSelectedIcon(icon : ImageButton, icons : List<ImageButton>) {
        icons.forEach{
            it.isSelected = false
        }
        icon.isSelected = true
    }

    private fun swapSubFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.subfragment_container, fragment)
            .commit()
    }
    // установить свой ip в baseUrl
    private fun setupRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.29:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(CharacterApi::class.java)
    }


    private fun sendCharacterToBackend() {
        val name = viewModel.name.value ?: return
        val specie = viewModel.race.value ?: return
        val characterClass = viewModel.characterClass.value ?: return
        val background = viewModel.background.value ?: return
        val stats = viewModel.abilities.value ?: return

        val abilities = stats.map {
            AbilityRequest(
                ability = it.ability,
                baseScore = it.baseScore,
                backgroundModifier = it.backgroundModifier
            )
        }

        val request = CharacterRequest(name, specie, characterClass, background, abilities)

        val gson = Gson()
        val json = gson.toJson(request)

        Log.d("Json_body", json)

        lifecycleScope.launch {
            try {
                val response = api.createCharacter(request)
                val message = if (response.isSuccessful) {
                    "Character created successfully!"
                } else {
                    "Error: ${response.code()}"
                }
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }




}