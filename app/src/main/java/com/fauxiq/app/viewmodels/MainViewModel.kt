package com.fauxiq.app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.fauxiq.app.data.local.TaskModel
import com.fauxiq.app.data.reposiotry.Repository
import com.fauxiq.app.data.reposiotry.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(val app: Application) : AndroidViewModel(app) {

    private var dataRepository = Repository(app)


    fun addTask(taskModel: TaskModel): LiveData<Resource<LongArray>> {

        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val data = dataRepository.addTaskToDb(taskModel)
            emit(data)
        }
    }

    fun deleteTask(taskModel: TaskModel) : LiveData<Resource<Any>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val data = dataRepository.delete(taskModel)
            emit(data)
        }
    }

    fun loadTasks(): LiveData<Resource<List<TaskModel>>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val data = dataRepository.getTasks()
            emit(data)
        }

    }


}