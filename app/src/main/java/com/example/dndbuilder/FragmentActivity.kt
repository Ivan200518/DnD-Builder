package com.example.dndbuilder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import androidx.room.RoomDatabase

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