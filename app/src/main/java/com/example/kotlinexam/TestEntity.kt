package com.example.kotlinexam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class TestEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var text : String = ""
        )