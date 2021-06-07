package com.example.kotlinexam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TestDAO {

    @Insert(onConflict = REPLACE)
    fun insert(entity: TestEntity)

    @Query("SELECT * FROM test")
    fun getAll() : List<TestEntity>

    @Delete
    fun delete(entity: TestEntity)

}