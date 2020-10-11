package com.example.doitapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doitapp.*
import com.google.android.material.card.MaterialCardView

class TaskTitleAdapter (private var taskTitleList : List<TaskTitle>, itemListener: RecyclerViewClickListener?) : RecyclerView.Adapter<TaskTitleAdapter.ViewHolder>() {

    interface RecyclerViewClickListener {
        fun onClick(taskTitle: String, id:Int)
        fun onLongClick(id: Int, title: String)
        fun showNumOfTask(id:Int) : Int
    }

    companion object {
        var itemClickListener : RecyclerViewClickListener? = null
    }

    init {
        itemClickListener = itemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_dates_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount() = taskTitleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = taskTitleList[position]

        holder.title.text = currentItem.taskTitle
        holder.tvDates.text = currentItem.date
        holder.numOfTask.text = itemClickListener?.showNumOfTask(currentItem.id).toString()

        holder.cardHolder.setOnClickListener {
            itemClickListener?.onClick(currentItem.taskTitle, currentItem.id)
        }

        holder.cardHolder.setOnLongClickListener {
            itemClickListener?.onLongClick(currentItem.id, currentItem.taskTitle)
            true
        }

    }

    fun updateSet(taskTitleList: List<TaskTitle>){
        this.taskTitleList = taskTitleList
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardHolder: MaterialCardView = itemView.findViewById(R.id.cdTaskContainer)
        val tvDates: TextView = itemView.findViewById(R.id.tvDates)
        val title: TextView = itemView.findViewById(R.id.taskParentName)
        var numOfTask: TextView = itemView.findViewById(R.id.tvNumOfTask)
    }

}
