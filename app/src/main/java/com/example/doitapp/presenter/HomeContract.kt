package com.example.doitapp.presenter

import com.example.doitapp.TaskTitle

interface HomeContract {

    interface Presenter : BasePresenter<View> {

        fun onGetListOfTaskTitle()
        fun onSaveTaskBtnClicked(newTask: String, date:String)
        fun onSaveUpdatedTaskBtnClicked(id:Int, updatedTaskTitle: String, date : String)
        fun onDelTaskBtnClicked(id:Int)
        fun onGetCurrentDate() : String
        fun onGetNumOfTask(id: Int):Int
    }

    interface View : BaseView {

        fun updateAdapter(taskList: MutableList<TaskTitle>)
        fun onLongClick(id: Int, taskTitle: String)
        fun setTaskTitleList(taskTitleList: MutableList<TaskTitle>)

    }

}