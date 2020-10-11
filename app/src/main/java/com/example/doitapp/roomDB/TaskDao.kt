package com.example.doitapp.roomDB

import androidx.room.*
import com.example.doitapp.*

@Dao
interface TaskDao {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM task_title_table ORDER by id desc") //get all the dates
    fun getListOfTaskTitle(): List<TaskTitle>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM task_list_table WHERE id_reference = :taskRefId") //get all the tasks for specific date
    fun getListOfTask(taskRefId: Int) : List<TaskListName>

    @Query("INSERT INTO task_title_table (task_title, date) Values(:taskTitle, :taskDate)") //Inserting new date
    fun insertNewTaskTitle(taskTitle: String ,taskDate: String)

    @Query("INSERT INTO task_list_table (id_reference, task_name) Values(:taskListIdRef, :taskName) ") // inserting new task
    fun insertNewTask(taskName: String, taskListIdRef : Int)

    @Query("DELETE FROM task_list_table where task_key = :taskKey") //deleting a task
    fun deleteTaskName (taskKey: Int)

    @Transaction
    @Query("DELETE FROM task_title_table where id = :id ") //deleting a task title
    fun deleteAll (id: Int)

    @Query("DELETE FROM task_list_table where id_reference = :id_reference")
    fun deleteAllTask(id_reference: Int)

    @Query("UPDATE task_list_table SET is_task_done = :state WHERE task_key = :taskKey")
    fun setTaskState(taskKey: Int, state: Boolean)

    @Query("UPDATE task_title_table SET task_title = :taskTitle, date = :date WHERE id = :id")
    fun updateTaskTitle(id: Int, taskTitle: String, date: String)

    @Query("UPDATE task_list_table SET task_name = :taskName WHERE task_key = :taskKey")
    fun updateTaskName(taskName: String, taskKey: Int)

    @Query ("SELECT task_title FROM task_title_table WHERE id = :id")
    fun getTaskTitle(id:Int) : String

    @Query ("SELECT date FROM task_title_table WHERE id = :id")
    fun getTaskTitleDate(id: Int) : String

    @Query ("SELECT COUNT(*) FROM TASK_LIST_TABLE WHERE id_reference = :id")
    fun getNumOfTask(id: Int) : Int


}