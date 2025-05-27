package com.example.dndbuilder.character

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
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
                selectButton(button,buttons)
                val className = button.text.toString().uppercase()
                viewModel.characterClass.value = className
            }
        }
        viewModel.characterClass.value?.let { savedClass ->
            buttons.find { btn ->
                btn.text.toString().uppercase() == savedClass
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
