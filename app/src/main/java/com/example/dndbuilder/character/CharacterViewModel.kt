package com.example.dndbuilder.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dndbuilder.stats.Stat
import com.example.dndbuilder.utils.Ability

class CharacterViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val race = MutableLiveData<String>()
    val characterClass = MutableLiveData<String>()
    val background = MutableLiveData<String>()
    val abilities = MutableLiveData<List<Ability>?>()



    val stats = MutableLiveData(
        listOf(
            Stat("STRENGTH"),
            Stat("DEXTERITY"),
            Stat("CONSTITUTION"),
            Stat("INTELLIGENCE"),
            Stat("WISDOM"),
            Stat("CHARISMA")
        )
    )

    fun saveAbilitiesFromStats(stats: List<Stat>?) {
        val abilityList = stats?.map { stat ->
            Ability(
                ability = stat.name,
                baseScore = stat.base,
                backgroundModifier = if (stat.editableMod) stat.mod else null
            )
        }
        abilities.value = abilityList
    }

}


