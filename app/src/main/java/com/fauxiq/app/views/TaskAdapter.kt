package com.fauxiq.app.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fauxiq.app.data.local.TaskModel
import com.fauxiq.app.databinding.TaskItemBinding

class TaskAdapter(private val tasks3: ArrayList<TaskModel>, val listener: OnRowClickListener ) :
    RecyclerView.Adapter<TaskAdapter.ItemHolder>() {

    fun addTask(taskModel: TaskModel) {
        tasks3.add(taskModel)
        notifyDataSetChanged()
    }

    fun addTasks(tasks: ArrayList<TaskModel>) {
        tasks3.addAll(tasks)
        notifyDataSetChanged()
    }

    fun clear() {
        tasks3.clear()
        notifyDataSetChanged()
    }

    /*fun deleteItem(position: Int, task: TaskModel){
        listener.onDeleteClick(position, task)
    }
*/
    fun removeTask(position: Int) {
        tasks3.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks3.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(tasks3[position], listener, position)
    }

    fun getSize(): Int {

        return tasks3.size
    }


    inner class ItemHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TaskModel, listener: OnRowClickListener, position: Int) {
            binding.task = item
            binding.listener = listener
            binding.position = position
            binding.executePendingBindings()
        }
    }
}