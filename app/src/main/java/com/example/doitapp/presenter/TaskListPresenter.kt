package com.example.doitapp.presenter

import com.example.doitapp.repository.TaskRepository

class TaskListPresenter (private val taskRepository: TaskRepository) : TaskListContract.Presenter {

    var view: TaskListContract.View? = null

    override fun onAddTaskFabClicked() {
        view?.showAddTaskDialog()
    }

    override fun onSaveTaskBtnClicked(newTask: String, id_reference: Int) {
        taskRepository.insertNewTask(newTask, id_reference)
        view?.updateAdapter(taskRepository.getListOfTask(id_reference).toMutableList())
    }

    override fun onSaveUpdatedTaskBtnClicked(taskKey: Int, taskName: String, id_reference: Int) {
        taskRepository.updateTaskName(taskName, taskKey)
        view?.updateAdapter(taskRepository.getListOfTask(id_reference).toMutableList())
    }

    override fun onDelTaskBtnClicked(taskKey: Int, id_reference : Int) {
        taskRepository.deleteTaskName(taskKey)
        view?.updateAdapter(taskRepository.getListOfTask(id_reference).toMutableList())
    }

    override fun onGetListOfTaskName(id: Int) {
        view?.showListOfTaskName(taskRepository.getListOfTask(id).toMutableList())
    }

    override fun onSaveTaskState(taskKey: Int, state: Boolean) {
        taskRepository.setTaskState(taskKey, state)
    }

    override fun onGetTaskTitle(id: Int): String {
        return taskRepository.getTaskTitle(id)
    }

    override fun onGetTaskTitleDate(id: Int): String {
        return taskRepository.getTaskTitleDate(id)
    }

    override fun onNavigateToHome() {
        view?.navigateToHome()
    }

    override fun onViewReady(view: TaskListContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }

}