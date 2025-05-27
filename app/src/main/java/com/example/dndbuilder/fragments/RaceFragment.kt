package com.example.dndbuilder.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dndbuilder.R
import com.example.dndbuilder.character.CharacterViewModel

class RaceFragment : Fragment(R.layout.fragment_race) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: CharacterViewModel by activityViewModels()

        val buttons = listOf(
            view.findViewById<Button>(R.id.btnAasimar),
            view.findViewById<Button>(R.id.btnDragonborn),
            view.findViewById<Button>(R.id.btnDwarf),
            view.findViewById<Button>(R.id.btnElf),
            view.findViewById<Button>(R.id.btnGnome),
            view.findViewById<Button>(R.id.btnGoliath),
            view.findViewById<Button>(R.id.btnHalfling),
            view.findViewById<Button>(R.id.btnHuman),
            view.findViewById<Button>(R.id.btnOrc),
            view.findViewById<Button>(R.id.btnTiefling)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                selectButton(button, buttons)

                val raceName = when (button.text.toString()) {
                    "Half-Orc" -> "HALF_ORC"
                    else -> button.text.toString().uppercase()
                }

                viewModel.race.value = raceName
            }
        }

        viewModel.race.value?.let { savedRace ->
            buttons.find { btn ->
                val raceName = when (btn.text.toString()) {
                    "Half-Orc" -> "HALF_ORC"
                    else -> btn.text.toString().uppercase()
                }
                raceName == savedRace
            }?.let { selectedButton ->
                selectButton(selectedButton, buttons)
            }
        }
    }
    private fun selectButton(selected: Button, allButtons: List<Button>) {
        allButtons.forEach {
            it.setBackgroundResource(R.drawable.character_button)
            it.setTextColor(ContextCompat.getColor(requireContext(), R.color.unselected_text))
            it.typeface = Typeface.DEFAULT
        }

        selected.setBackgroundResource(R.drawable.character_button_background_selected)
        selected.setTextColor(ContextCompat.getColor(requireContext(), R.color.selected_text))
        selected.typeface = Typeface.DEFAULT_BOLD
    }

}