package com.example.dndbuilder.character


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("specie")
    val specie: String,

    @SerializedName("characterClass")
    val characterClass: String,

    @SerializedName("background")
    val background: String,

    @SerializedName("abilities")
    val abilities: List<AbilityResponse>
)
