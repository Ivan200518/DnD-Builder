package com.example.dndbuilder.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
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
                val raceName = when (button.text.toString()) {
                    "Half-Orc" -> "HALF_ORC"
                    else -> button.text.toString().uppercase()
                }

                viewModel.race.value = raceName
            }
        }
    }
}