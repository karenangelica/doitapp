package com.example.doitapp.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.doitapp.TaskListName
import com.example.doitapp.TaskTitle

@Database(entities = [TaskTitle::class, TaskListName::class], version = 6 , exportSchema = false)

public abstract class TaskRoomDatabase : RoomDatabase (){

    abstract fun taskDao() : TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context) : TaskRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, TaskRoomDatabase::class.java, "task_database").fallbackToDestructiveMigration().allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}