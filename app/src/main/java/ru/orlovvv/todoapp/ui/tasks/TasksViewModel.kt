package ru.orlovvv.todoapp.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.orlovvv.todoapp.data.db.TaskDao
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val taskDao: TaskDao) : ViewModel() {

    val task = taskDao.getAllTasks().asLiveData()

}