package com.example.doitapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doitapp.adapters.TaskTitleAdapter
import com.example.doitapp.databinding.ActivityHomeBinding
import com.example.doitapp.dependencyInjector.DependencyInjectorImpl
import com.example.doitapp.presenter.HomeContract
import com.example.doitapp.presenter.HomePresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeActivity : AppCompatActivity(), TaskTitleAdapter.RecyclerViewClickListener,
    HomeContract.View {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: TaskTitleAdapter
    private lateinit var presenter: HomePresenter

    private lateinit var taskTitleList: MutableList<TaskTitle>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = HomePresenter(DependencyInjectorImpl.taskRepository(this))
        presenter.onViewReady(this)


        adapter = TaskTitleAdapter(taskTitleList, this)
        binding.rvDates.adapter = adapter
        binding.rvDates.layoutManager = LinearLayoutManager(this)
        binding.rvDates.setHasFixedSize(true)

        setupFabListener()
    }

    override fun onResume() {
        presenter.onGetListOfTaskTitle()
        updateAdapter(taskTitleList)
        super.onResume()
    }

    private fun setupFabListener() {

        binding.addTaskTitleFloatBtn.setOnClickListener {

            val view: View =
                layoutInflater.inflate(R.layout.add_task_dialog, findViewById(R.id.content), false)
            val dialog = MaterialAlertDialogBuilder(this).setView(view).setCancelable(true).create()
            dialog.show()


            view.findViewById<Button>(R.id.saveTaskBtn).setOnClickListener {
                val newTask = view.findViewById<EditText>(R.id.newTaskET).text.toString()
                presenter.onSaveTaskBtnClicked(newTask, getCurrentDate())
                dialog.dismiss()

            }
        }
    }

    override fun setTaskTitleList(taskTitleList: MutableList<TaskTitle>) {
        this.taskTitleList = taskTitleList
    }


    private fun getCurrentDate(): String {
        return presenter.onGetCurrentDate()
    }

    override fun updateAdapter(taskTitleList: MutableList<TaskTitle>) {
        adapter.updateSet(taskTitleList)
        adapter.notifyDataSetChanged()
    }


    /*                       Adapter Interface                           */
    override fun onClick(taskTitle: String, id: Int) {

        var intent = Intent(this@HomeActivity, TaskListActivity::class.java)
        intent.putExtra("taskListRefId", id)
        startActivity(intent)
    }

    override fun onLongClick(id: Int, taskTitle: String) {

        val view: View =
            layoutInflater.inflate(R.layout.edit_task_title_dialog, findViewById(R.id.content), false)
        val dialog = MaterialAlertDialogBuilder(this).setView(view).setCancelable(true).create()
        view.findViewById<EditText>(R.id.newTaskET).setText(taskTitle)
        dialog.show()

        view.findViewById<Button>(R.id.saveUpdatedTaskBtn).setOnClickListener {
            val updatedTaskTitle = view.findViewById<EditText>(R.id.newTaskET).text.toString()
            presenter.onSaveUpdatedTaskBtnClicked(id, updatedTaskTitle, getCurrentDate())
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.delTaskBtn).setOnClickListener {
            presenter.onDelTaskBtnClicked(id)
            dialog.dismiss()
        }

    }

    override fun showNumOfTask(id: Int): Int {
        return presenter.onGetNumOfTask(id)
    }


}
