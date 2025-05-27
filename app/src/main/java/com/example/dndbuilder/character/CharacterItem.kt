package com.example.dndbuilder.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterItem(
    val name: String,
    val specie : String,
    val background : String,
    val id : Int = 0,
    val characterClass: String,
    // Добавь остальные поля, которые нужны для экрана с деталями
    val race: String? = null,
    val str: Int? = null,
    val dex: Int? = null,
    val con: Int? = null,
    val intelligence: Int? = null,
    val wisdom: Int? = null,
    val charisma: Int? = null,
    val imageResId: Int? = null
) : Parcelable
