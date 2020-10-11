package com.example.doitapp

import androidx.room.*

data class Task (
    @Embedded var taskDate: TaskTitle,
    @Relation (
        parentColumn = "id",
        entityColumn =  "id_reference"
    )
    val taskListName : List<TaskListName>
)

@Entity(tableName = "task_title_table")
data class TaskTitle (
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name="task_title") var taskTitle : String,
    @ColumnInfo(name="date") var date : String,
    @ColumnInfo(name="is_done")  var isDone : Boolean?
)

@Entity(tableName = "task_list_table")
data class TaskListName(
    @PrimaryKey @ColumnInfo(name = "task_key") val taskKey: Int = 0,
    @ColumnInfo(name = "id_reference") val taskListIdRef: Int = 0,
    @ColumnInfo(name = "task_name") var taskName: String,
    @ColumnInfo(name = "is_task_done") var isDone: Boolean?
)
