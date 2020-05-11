package com.fauxiq.app.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TasksDao {

    @Query("SELECT * FROM  tasks_table WHERE id = :id")
    public fun get(id: Int): LiveData<TaskModel>

    @Query("SELECT * FROM  tasks_table")
    fun getAll(): List<TaskModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tasks: TaskModel): LongArray

    @Delete
    fun delete(jobModel: TaskModel)

    @Query("DELETE FROM tasks_table")
    fun deleteAll()
}