package com.example.dndbuilder.character

import com.google.gson.annotations.SerializedName

data class AbilityResponse(
    @SerializedName("ability")
    val ability: String,

    @SerializedName("baseScore")
    val baseScore: Int,

    @SerializedName("backgroundModifier")
    val backgroundModifier: Int? = null
)