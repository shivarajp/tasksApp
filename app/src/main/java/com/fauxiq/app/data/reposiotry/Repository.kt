package com.fauxiq.app.data.reposiotry

import android.app.Application
import com.fauxiq.app.data.local.TaskModel
import com.fauxiq.app.data.local.TasksDatabase

class Repository(val app: Application) {

    val tasksDao = TasksDatabase.getDatabase(app).tasksDao()
    private val responseHandler: ResponseHandler = ResponseHandler()

    fun addTaskToDb(taskModel: TaskModel): Resource<LongArray> {
        return try {
            val response = tasksDao.insertAll(taskModel)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            //just extra logging
            responseHandler.handleException(e)
        }
    }

    fun getTasks(): Resource<List<TaskModel>> {

        return try {
            val response = tasksDao.getAll()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            //just extra logging
            responseHandler.handleException(e)
        }
    }

    fun delete(taskModel: TaskModel): Resource<Any> {

        return try {
            val response = tasksDao.delete(taskModel)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            //just extra logging
            responseHandler.handleException(e)
        }
    }
}