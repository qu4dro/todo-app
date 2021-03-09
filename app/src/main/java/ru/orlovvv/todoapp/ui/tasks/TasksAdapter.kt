package ru.orlovvv.todoapp.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.todoapp.data.db.entities.TaskItem
import ru.orlovvv.todoapp.databinding.ItemTasksBinding

class TasksAdapter : ListAdapter<TaskItem, TasksAdapter.TasksViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksAdapter.TasksViewHolder {
        val binding = ItemTasksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksAdapter.TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class TasksViewHolder(private val binding: ItemTasksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskItem) {
            binding.apply {
                cbTaskCompleted.isChecked = task.isCompleted
                tvTaskName.text = task.name
                tvTaskName.paint.isStrikeThruText = task.isCompleted
                ivPriority.isVisible = task.isImportant
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<TaskItem>() {
        override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem) =
            oldItem == newItem

    }

}