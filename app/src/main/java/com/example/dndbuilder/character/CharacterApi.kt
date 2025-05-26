package com.example.dndbuilder.character

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CharacterApi {
    @POST("characters")
    suspend fun createCharacter(@Body character: CharacterRequest): Response<Void>
} 