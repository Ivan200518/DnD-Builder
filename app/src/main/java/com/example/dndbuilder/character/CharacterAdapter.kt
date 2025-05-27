package com.example.dndbuilder.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dndbuilder.R

class CharacterAdapter :
    ListAdapter<CharacterItem, CharacterViewHolder>(CharacterItemDiffCallback()) {

    var onCharacterClickListener: ((CharacterItem) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_character_class, // используем твой новый xml макет
            parent,
            false
        )
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterItem = getItem(position)
        holder.bind(characterItem)

        holder.itemView.setOnClickListener {
            onCharacterClickListener?.invoke(characterItem)
        }

    }
}
