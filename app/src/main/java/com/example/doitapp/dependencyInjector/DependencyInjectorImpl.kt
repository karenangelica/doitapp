package com.example.doitapp.dependencyInjector

import android.content.Context
import com.example.doitapp.repository.TaskRepository
import com.example.doitapp.repository.TaskRepositoryImpl
import com.example.doitapp.roomDB.TaskRoomDatabase

object DependencyInjectorImpl : DependencyInjector {

    override fun taskRepository(context: Context): TaskRepository {
        return TaskRepositoryImpl(TaskRoomDatabase.getDatabase(context).taskDao())
    }
}