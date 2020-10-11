package com.example.doitapp.repository

import com.example.doitapp.TaskListName
import com.example.doitapp.TaskTitle
import com.example.doitapp.roomDB.TaskDao

class TaskRepositoryImpl (private val taskDao: TaskDao) : TaskRepository {

    override fun getListOfTaskTitle(): List<TaskTitle> {
        return taskDao.getListOfTaskTitle()
    }
    override fun getListOfTask(taskRefId : Int): List<TaskListName> {
        return taskDao.getListOfTask(taskRefId)
    }

    override fun insertNewTaskTitle(taskTitle: String, taskDate: String) {
        taskDao.insertNewTaskTitle(taskTitle, taskDate)
    }

    override fun insertNewTask(task: String, taskListRefId : Int) {
        taskDao.insertNewTask(task, taskListRefId)
    }

    override fun deleteTaskName(taskKey: Int) {
        taskDao.deleteTaskName(taskKey)
    }

    override fun deleteAllTask(id_reference: Int) {
        taskDao.deleteTaskName(id_reference)
    }

    override fun deleteAll(id: Int) {
        taskDao.deleteAll(id)
    }

    override fun setTaskState(taskKey: Int, state: Boolean) {
        taskDao.setTaskState(taskKey, state)
    }

    override fun updateTaskTitle(id: Int, taskTitle: String, date:String) {
        taskDao.updateTaskTitle(id, taskTitle, date)
    }

    override fun updateTaskName(taskName: String, taskKey: Int) {
        taskDao.updateTaskName(taskName, taskKey)
    }

    override fun getTaskTitle(id: Int): String {
        return taskDao.getTaskTitle(id)
    }

    override fun getTaskTitleDate(id: Int) : String {
        return taskDao.getTaskTitleDate(id)
    }

    override fun getNumOfTask(id: Int): Int {
        return taskDao.getNumOfTask(id)
    }

}