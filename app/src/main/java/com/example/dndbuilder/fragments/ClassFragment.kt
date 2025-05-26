package com.example.dndbuilder.character

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dndbuilder.R

class ClassFragment : Fragment(R.layout.fragment_class) {

    private val viewModel: CharacterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttons = listOf(
            view.findViewById<Button>(R.id.btnBarbarian),
            view.findViewById<Button>(R.id.btnBard),
            view.findViewById<Button>(R.id.btnCleric),
            view.findViewById<Button>(R.id.btnDruid),
            view.findViewById<Button>(R.id.btnFighter),
            view.findViewById<Button>(R.id.btnMonk),
            view.findViewById<Button>(R.id.btnPaladin),
            view.findViewById<Button>(R.id.btnRanger),
            view.findViewById<Button>(R.id.btnRogue),
            view.findViewById<Button>(R.id.btnSorcerer),
            view.findViewById<Button>(R.id.btnWarlock)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                val className = button.text.toString().uppercase()
                viewModel.characterClass.value = className
            }
        }
    }
}
