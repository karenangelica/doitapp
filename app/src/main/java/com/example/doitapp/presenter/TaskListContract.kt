package com.example.doitapp.presenter

import com.example.doitapp.TaskListName

interface TaskListContract {

    interface Presenter : BasePresenter<View> {

        fun onAddTaskFabClicked()
        fun onSaveTaskBtnClicked(newTask: String, id_reference: Int)
        fun onSaveUpdatedTaskBtnClicked(taskKey: Int, taskName: String, id_reference: Int)
        fun onDelTaskBtnClicked(taskKey: Int, id_reference: Int)
        fun onGetListOfTaskName(id:Int)
        fun onSaveTaskState( taskKey: Int, state: Boolean)
        fun onGetTaskTitle(id: Int) : String
        fun onGetTaskTitleDate(id: Int) : String
        fun onNavigateToHome()
    }

    interface View : BaseView  {

        fun showAddTaskDialog()
        fun updateAdapter(taskListName : MutableList<TaskListName>)
        fun onClick(taskKey : Int, sate : Boolean)
        fun onLongClick(taskKey: Int, taskName : String)
        fun showListOfTaskName(taskListName : MutableList<TaskListName>)
        fun navigateToHome()

    }
}