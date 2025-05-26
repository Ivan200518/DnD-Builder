package com.example.dndbuilder.fragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.dndbuilder.R
import com.example.dndbuilder.utils.AppDatabase

class FragmentActivity : AppCompatActivity() {
    lateinit var db : AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupDatabase()
        val userDao = db.userDao()
    }

    fun setupDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-database"
        ).build()
    }
}