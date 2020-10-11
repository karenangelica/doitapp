package com.example.doitapp.presenter

import com.example.doitapp.repository.TaskRepository
import java.text.SimpleDateFormat
import java.util.*

class HomePresenter( private val taskRepository : TaskRepository) : HomeContract.Presenter {

    var view: HomeContract.View? = null

    override fun onViewReady(view: HomeContract.View) {
        this.view = view
        view?.setTaskTitleList(taskRepository.getListOfTaskTitle().toMutableList())
    }

    override fun onViewDetach() {
        view = null
    }

    override fun onGetListOfTaskTitle() {
        view?.setTaskTitleList(taskRepository.getListOfTaskTitle().toMutableList())
    }

    override fun onSaveTaskBtnClicked(newTask: String, date: String) {
        taskRepository.insertNewTaskTitle(newTask, date)
        view?.updateAdapter(taskRepository.getListOfTaskTitle().toMutableList())
    }

    override fun onSaveUpdatedTaskBtnClicked(id: Int, updatedTaskTitle: String, date: String) {
        taskRepository.updateTaskTitle(id, updatedTaskTitle, date)
        view?.updateAdapter(taskRepository.getListOfTaskTitle().toMutableList())
    }

    override fun onDelTaskBtnClicked(id: Int) {
        taskRepository.deleteAll(id)
        taskRepository.deleteAllTask(id)
        view?.updateAdapter(taskRepository.getListOfTaskTitle().toMutableList())
    }

    override fun onGetCurrentDate(): String {
        val sdf = SimpleDateFormat("d MMM YYYY")
        return sdf.format(Date())
    }

    override fun onGetNumOfTask(id: Int): Int {
        return taskRepository.getNumOfTask(id)
    }

}