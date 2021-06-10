package com.unhas.ac.id.roomdb.crud.myapps.db

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unhas.ac.id.roomdb.crud.myapps.viewmodel.TaskViewModel
import com.unhas.ac.id.roomdb.crud.myapps.databinding.ListitemTaskBinding
import kotlinx.android.synthetic.main.listitem_task.*

class TaskAdapter(private val taskModel: TaskViewModel) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallBack()) {
    private var lists = listOf<Task>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskAdapter.TaskViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemTaskBinding.inflate(inflater)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.titleText.text = getItem(position).title
        holder.descText.text = getItem(position).description


    }



    // override fun getItemCount() = viewModel.todos.value!!.size
class TaskViewHolder(val binding: ListitemTaskBinding ) : RecyclerView.ViewHolder(binding.root) {

        val titleText = binding.titleTask
        val descText = binding.taskDescription

    }
}


class TaskDiffCallBack: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.equals(newItem)
    }


}