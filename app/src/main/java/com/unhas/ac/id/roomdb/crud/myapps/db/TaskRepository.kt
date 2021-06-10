package com.unhas.ac.id.roomdb.crud.myapps.db

import androidx.lifecycle.LiveData

class TaskRepository(private val TaskDao:TaskDAO) {

    val allTasks = TaskDao.loadTask()

    suspend fun insertTask(task: Task){
        TaskDao.insert(task)
    }
    suspend fun deleteTask(task: Task) {
        TaskDao.delete(task)
    }
    suspend fun updateTask(task: Task){
        TaskDao.update(task)
    }
    fun search(title: String) : LiveData<List<Task>>?{
        return TaskDao?.search(title)
    }

}