package com.example.dndbuilder.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndbuilder.stats.Stat
import com.example.dndbuilder.utils.Ability
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val race = MutableLiveData<String>()
    val characterClass = MutableLiveData<String>()
    val background = MutableLiveData<String>()
    val abilities = MutableLiveData<List<Ability>?>()
    private lateinit var retrofit : Retrofit
    private lateinit var api: CharacterApi

    init {
        setupRetrofit()
    }

    private val _characters = MutableLiveData<List<CharacterResponse>>()
    val characters : LiveData<List<CharacterResponse>> = _characters

    private val _character = MutableLiveData<CharacterResponse>()

    val character : LiveData<CharacterResponse> = _character

    fun setCharacter(response: CharacterResponse) {
        _character.value = response
    }
    private fun setupRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.29:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(CharacterApi::class.java)
    }

    fun loadAllCharacters() {
        viewModelScope.launch {
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    response.body()?.let { charactersList ->
                        _characters.value = charactersList
                        Log.d("Characters", "Loaded ${charactersList.size} characters")
                        charactersList.forEach {
                            Log.d("Character", "Name: ${it.name}, Class: ${it.characterClass}")
                        }
                    }
                } else {
                    Log.e("API", "Failed to load characters: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception while loading characters: ${e.message}")
            }
        }
    }

    fun loadCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                val response = api.getCharacterById(id)
                if (response.isSuccessful) {
                    response.body()?.let { character ->
                        _character.value = character
                        Log.d("Character", "Loaded: ${character.name}")
                        character.abilities.forEach {
                            Log.d("Ability", "${it.ability}: ${it.baseScore}, modifier: ${it.backgroundModifier}")
                        }
                    }
                } else {
                    Log.e("API", "Failed: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception: ${e.message}")
            }
        }
    }

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


