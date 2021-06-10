package com.unhas.ac.id.roomdb.crud.myapps.db

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unhas.ac.id.roomdb.crud.myapps.R
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


        //Delete
        holder.delBtn.setOnClickListener {
            taskModel.removeTask(getItem(holder.adapterPosition))
        }
        //Edit
        holder.editBtn.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.editlayout_task,null)

            //Take data
            val prevTitle =getItem(position).title
            val prevDesc = getItem(position).description
            val editTitle = view.findViewById<TextView>(R.id.editTitle)
            editTitle.text = prevTitle
            val editDesc = view.findViewById<TextView>(R.id.editTask)
            editDesc.text = prevDesc
            val btnUpdate = view.findViewById<TextView>(R.id.add_button)
            val btnCancel = view.findViewById<TextView>(R.id.cancel_button)
          


            //Make Dialog
            val alertDialog = AlertDialog.Builder(context).setView(view).show()
            btnUpdate.setOnClickListener {
                val editsTitle = editTitle.text.toString()
                val editsDesc = editDesc.text.toString()
                getItem(holder.adapterPosition).title = editsTitle
                getItem(holder.adapterPosition).description = editsDesc
                

                taskModel.updateTask(getItem(holder.adapterPosition))
                holder.titleText.text = editsTitle
                holder.descText.text = editsDesc

                alertDialog.dismiss()
            }
            btnCancel.setOnClickListener{
                alertDialog.dismiss()
            }
            alertDialog.create()
        }
    }


class TaskViewHolder(val binding: ListitemTaskBinding ) : RecyclerView.ViewHolder(binding.root) {

        val titleText = binding.titleTask
        val descText = binding.taskDescription
        val delBtn = binding.delBtn
        val editBtn = binding.editBtn


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