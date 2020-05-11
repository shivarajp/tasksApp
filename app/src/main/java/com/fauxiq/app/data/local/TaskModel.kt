package com.fauxiq.app.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks_table")
class TaskModel (
    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var id: Int,
    @field:ColumnInfo(name = "name")
    val name: String,
    @field:ColumnInfo(name = "rate")
    val rate: Float,
    @field:ColumnInfo(name = "qty")
    val qty: Float,
    @field:ColumnInfo(name = "amount")
    val amount: Float
): Serializable