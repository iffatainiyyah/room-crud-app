package com.unhas.ac.id.roomdb.crud.myapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unhas.ac.id.roomdb.crud.myapps.databinding.ActivityMainBinding
import com.unhas.ac.id.roomdb.crud.myapps.db.TaskAdapter
import com.unhas.ac.id.roomdb.crud.myapps.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TaskAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var taskModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        taskModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = TaskAdapter(taskModel)
        recyclerView = binding.TaskRV
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}