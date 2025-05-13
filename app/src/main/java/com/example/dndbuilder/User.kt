package com.example.dndbuilder

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey val id : Int = 0,
    val username : String,
    val password : String
)