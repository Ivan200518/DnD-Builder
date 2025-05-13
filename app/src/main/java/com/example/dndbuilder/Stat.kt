package com.example.dndbuilder

data class Stat(
    val name: String,
    var base: Int = 0,
    var race: Int = 0,
    var other: Int = 0,
    var mod: Int = 0,
    var editableMod: Boolean = false
)

