package com.unhas.ac.id.roomdb.crud.myapps.db

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

}