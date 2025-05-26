package com.example.dndbuilder.character

import com.example.dndbuilder.utils.Ability
import com.google.gson.annotations.SerializedName


data class CharacterRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("specie")
    val specie: String,

    @SerializedName("characterClass")
    val characterClass: String,

    @SerializedName("background")
    val background: String,

    @SerializedName("abilities")
    val abilities: List<AbilityRequest>
)