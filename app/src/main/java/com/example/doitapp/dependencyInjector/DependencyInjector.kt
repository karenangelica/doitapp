package com.example.doitapp.dependencyInjector

import android.content.Context
import com.example.doitapp.repository.TaskRepository

interface DependencyInjector {

    fun taskRepository (context : Context) : TaskRepository
}