package com.example.doitapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.doitapp.R
import com.example.doitapp.TaskListName


class TaskListAdapter(private var taskListName: MutableList<TaskListName>, itemListener: RecyclerViewClickListener?) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    interface RecyclerViewClickListener {
        fun onClick(taskKey : Int, state: Boolean)
        fun onLongClick(taskKey: Int, taskName: String)
    }

    companion object {
        var itemClickListener : RecyclerViewClickListener? = null
    }

    init {
        itemClickListener = itemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_task_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = taskListName.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = taskListName[position]

        holder.cbTask.text = currentItem.taskName

        if (currentItem.isDone == true ) {
            holder.cbTask.isChecked = true
            holder.cbTask.setTextColor(AppCompatResources.getColorStateList(holder.cbTask.context, R.color.cb_checked_color))
        }

        holder.cbTask.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                holder.cbTask.isChecked = true
                holder.cbTask.setTextColor(AppCompatResources.getColorStateList(holder.cbTask.context, R.color.cb_checked_color))
                itemClickListener?.onClick(currentItem.taskKey, true)
            }
            else {
                holder.cbTask.isChecked = false
                holder.cbTask.setTextColor(AppCompatResources.getColorStateList(holder.cbTask.context, R.color.default_black))
                itemClickListener?.onClick(currentItem.taskKey, false)
            }
        }

        holder.cbTask.setOnLongClickListener {
            itemClickListener?.onLongClick(currentItem.taskKey, currentItem.taskName)
            true
        }

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val cbTask : CheckBox = itemView.findViewById(R.id.cbTask)
    }

    fun updateSet(taskListName: MutableList<TaskListName> ) {
        this.taskListName = taskListName
    }

}