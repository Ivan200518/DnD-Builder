package com.example.dndbuilder.character

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CharacterApi {
    @POST("characters")
    suspend fun createCharacter(@Body character: CharacterRequest): Response<Void>

    @GET("characters")
    suspend fun getCharacters() : Response<List<CharacterResponse>>

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id : Int) : Response<CharacterResponse>
} 