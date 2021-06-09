package com.unhas.ac.id.roomdb.crud.myapps.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {
    @Query("SELECT * FROM task")

    fun loadTask() : LiveData<List<Task>>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}