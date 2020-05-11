package com.fauxiq.app.views

import com.fauxiq.app.data.local.TaskModel
import java.text.FieldPosition

interface OnRowClickListener {

    fun onDeleteClick(position: Int, taskModel : TaskModel)
    fun onCardClicked(position: Int, taskModel : TaskModel)

}
