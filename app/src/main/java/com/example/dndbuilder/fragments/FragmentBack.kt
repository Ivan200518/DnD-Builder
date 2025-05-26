package com.example.dndbuilder.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
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
                val backgroundName = button.text.toString().uppercase()
                viewModel.background.value = backgroundName
            }
        }
    }
}
