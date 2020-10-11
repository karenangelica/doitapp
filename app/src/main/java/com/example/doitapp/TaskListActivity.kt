package com.example.doitapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doitapp.adapters.TaskListAdapter
import com.example.doitapp.databinding.ActivityTaskListBinding
import com.example.doitapp.dependencyInjector.DependencyInjectorImpl
import com.example.doitapp.presenter.TaskListContract
import com.example.doitapp.presenter.TaskListPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TaskListActivity : AppCompatActivity(), TaskListAdapter.RecyclerViewClickListener , TaskListContract.View{

    private lateinit var binding: ActivityTaskListBinding
    private lateinit var adapter: TaskListAdapter
    private lateinit var presenter : TaskListPresenter
    private var taskListRefId = 0

    private lateinit var taskListName : MutableList<TaskListName>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent

        taskListRefId = intent.getIntExtra("taskListRefId", 0)

        presenter = TaskListPresenter(DependencyInjectorImpl.taskRepository(this))
        presenter.onViewReady(this)
        presenter.onGetListOfTaskName(taskListRefId)


        binding.taskTitle.text = presenter.onGetTaskTitle(taskListRefId)
        binding.date.text = presenter.onGetTaskTitleDate(taskListRefId)

        adapter = TaskListAdapter(taskListName, this)
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(this)
        binding.rvTask.setHasFixedSize(true)

        setupListener()

    }


    private fun setupListener () {

        binding.addTaskFloatBtn.setOnClickListener {

            presenter.onAddTaskFabClicked()
        }

        binding.homeBtn.setOnClickListener {

            presenter.onNavigateToHome()
        }

    }

    override fun showListOfTaskName(taskListName: MutableList<TaskListName>) {
        this.taskListName = taskListName
    }

    override fun navigateToHome() {

        var intent = Intent(this@TaskListActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun showAddTaskDialog() {

        val view: View = layoutInflater.inflate(R.layout.add_task_dialog, findViewById(R.id.content), false)
        val dialog = MaterialAlertDialogBuilder(this).setView(view).setCancelable(true).create()
        dialog.show()

        view.findViewById<Button>(R.id.saveTaskBtn).setOnClickListener {
            val newTask = view.findViewById<EditText>(R.id.newTaskET).text.toString()
            presenter.onSaveTaskBtnClicked(newTask, taskListRefId)
            println(taskListRefId)
            dialog.dismiss()
        }
    }

    override fun updateAdapter(taskListName: MutableList<TaskListName>) {

        this.taskListName = taskListName
        adapter.updateSet(taskListName)
        adapter.notifyDataSetChanged()
    }


    override fun onClick(taskKey: Int, state: Boolean) {
        presenter.onSaveTaskState(taskKey, state)
    }

    override fun onLongClick(taskKey: Int, taskName: String) {

        val view: View = layoutInflater.inflate(R.layout.edit_task_dialog, findViewById(R.id.content), false)
        val dialog = MaterialAlertDialogBuilder(this).setView(view).setCancelable(true).create()
        view.findViewById<EditText>(R.id.newTaskET).setText(taskName)
        dialog.show()

        view.findViewById<Button>(R.id.saveUpdatedTaskBtn).setOnClickListener {
            val updatedTaskName = view.findViewById<EditText>(R.id.newTaskET).text.toString()
            presenter.onSaveUpdatedTaskBtnClicked(taskKey, updatedTaskName, taskListRefId)
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.delTaskBtn).setOnClickListener {
            presenter.onDelTaskBtnClicked(taskKey, taskListRefId)
            dialog.dismiss()
        }

    }

}