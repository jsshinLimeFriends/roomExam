package com.example.kotlinexam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TestEntity::class), version = 1)
abstract class TestDatabase : RoomDatabase() {
    abstract fun testDAO() : TestDAO

    companion object {
        var INSTANCE : TestDatabase? = null

        fun getInstance(context: Context) : TestDatabase? {
            if (INSTANCE == null) {
                synchronized(TestDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    TestDatabase::class.java, "test.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }

    }
}