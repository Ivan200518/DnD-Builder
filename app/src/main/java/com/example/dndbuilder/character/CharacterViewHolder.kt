package com.example.dndbuilder.character

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.dndbuilder.R

class CharacterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val imageCharacter = view.findViewById<ImageView>(R.id.imageCharacter)
    private val textCharacterClass = view.findViewById<TextView>(R.id.textCharacter)

    fun bind(character: CharacterItem) {
        textCharacterClass.text = character.name

        val imageRes = when (character.characterClass.uppercase()) {
            "FIGHTER" -> R.drawable.fighter_image_icon
            "BARBARIAN" -> R.drawable.barbarian_icon
            "BARD" -> R.drawable.bard_icon
            "DRUID" -> R.drawable.druid_icon
            "MONK" -> R.drawable.monk_icon
            "PALADIN" -> R.drawable.paladin_icon
            "ROGUE" -> R.drawable.rogue_icon
            "CLERIC" -> R.drawable.cleric_icon
            "SORCERER" -> R.drawable.sorcerer_icon
            "RANGER" -> R.drawable.ranger_icon
            "WARLOCK" -> R.drawable.warlock_icon
            else -> R.drawable.default_icon
        }
        imageCharacter.setImageResource(imageRes)
    }
}
