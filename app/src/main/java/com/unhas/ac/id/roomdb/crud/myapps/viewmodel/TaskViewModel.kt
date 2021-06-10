package com.unhas.ac.id.roomdb.crud.myapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.unhas.ac.id.roomdb.crud.myapps.db.Task
import com.unhas.ac.id.roomdb.crud.myapps.db.TaskDAO
import com.unhas.ac.id.roomdb.crud.myapps.db.TaskDB
import com.unhas.ac.id.roomdb.crud.myapps.db.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : TaskRepository
    private val taskDao : TaskDAO
    private lateinit var _tasks: LiveData<List<Task>>
    val listTasks: LiveData<List<Task>>
        get() = _tasks

    //coroutines
    private var taskJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + taskJob)

    init {
        taskDao = TaskDB.getInstance(application).taskDao()
        repository = TaskRepository(taskDao)
        _tasks = repository.allTasks
    }

    override fun onCleared() {
        super.onCleared()
        taskJob.cancel()
    }

    fun createTasks(
        text: String,
        text1: String

    ) {
        uiScope.launch {
            repository.insertTask(Task(0, text, text1))
        }
    }

    fun getData():LiveData<List<Task>>{
        return listTasks
    }

    fun removeTask(task: Task) {
        uiScope.launch {
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        uiScope.launch {
            repository.updateTask(task)
        }
    }


}