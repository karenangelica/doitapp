package com.example.doitapp.repository

import com.example.doitapp.TaskListName
import com.example.doitapp.TaskTitle


interface TaskRepository {

    fun getListOfTaskTitle() : List<TaskTitle>
    fun getListOfTask(taskRefId : Int) : List <TaskListName>
    fun insertNewTaskTitle(taskTitle: String, date: String)
    fun insertNewTask(task: String, taskListRefId : Int)
    fun deleteTaskName(taskKey: Int)
    fun deleteAllTask(id_reference: Int)
    fun deleteAll (id: Int)
    fun setTaskState(taskKey: Int, state: Boolean)
    fun updateTaskTitle(id: Int, taskTitle: String, date: String)
    fun updateTaskName(taskName: String, taskKey: Int)

    fun getTaskTitle(id: Int) : String
    fun getTaskTitleDate(id: Int)  : String
    fun getNumOfTask(id: Int) : Int
}