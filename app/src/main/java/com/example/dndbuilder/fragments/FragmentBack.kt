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

class FragmentBack : Fragment(R.layout.fragment_background) {

    private val viewModel: CharacterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttons = listOf(
            view.findViewById<Button>(R.id.btnAcolyte),
            view.findViewById<Button>(R.id.btnArtisan),
            view.findViewById<Button>(R.id.btnCharlatan),
            view.findViewById<Button>(R.id.btnCriminal),
            view.findViewById<Button>(R.id.btnEntertainer),
            view.findViewById<Button>(R.id.btnFarmer),
            view.findViewById<Button>(R.id.btnGuard),
            view.findViewById<Button>(R.id.btnGuide),
            view.findViewById<Button>(R.id.btnHermit),
            view.findViewById<Button>(R.id.btnMerchant),
            view.findViewById<Button>(R.id.btnNoble),
            view.findViewById<Button>(R.id.btnSage),
            view.findViewById<Button>(R.id.btnSailor),
            view.findViewById<Button>(R.id.btnScribe),
            view.findViewById<Button>(R.id.btnSoldier),
            view.findViewById<Button>(R.id.btnWayfarer)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                selectButton(button, buttons)
                val backgroundName = button.text.toString().uppercase()
                viewModel.background.value = backgroundName
            }
        }

        viewModel.background.value?.let { savedBack ->
            buttons.find{ btn ->
                btn.text.toString().uppercase() == savedBack
            }?.let { selectButton ->
                selectButton(selectButton,buttons)
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
