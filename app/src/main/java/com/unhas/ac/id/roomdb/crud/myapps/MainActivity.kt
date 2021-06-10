package com.unhas.ac.id.roomdb.crud.myapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        taskModel.getData().observe(this, Observer { list ->
            viewAdapter.submitList(list)
        })


        val newBtn = binding.btnNewtask
        val inflaters = LayoutInflater.from(this)
        val viewTask = inflaters.inflate(R.layout.activity_main, null)

        // Add data
        newBtn.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.newlayout_task, null)
            val newTitle = view.findViewById<TextView>(R.id.editTitle)
            val newDesc = view.findViewById<TextView>(R.id.editTask)
            val addBtn = view.findViewById<TextView>(R.id.add_button)
            val cancelBtn = view.findViewById<TextView>(R.id.cancel_button)


            //Dialog
            var alertDialog = AlertDialog.Builder(this).setView(view).show()

            // Add new
            addBtn.setOnClickListener {
                taskModel.createTasks(
                    newTitle.text.toString(),
                    newDesc.text.toString()

                )
                alertDialog.dismiss()

            }
            cancelBtn.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.create()
        }


    }

}