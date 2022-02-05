package br.com.isoftware.todolist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.isoftware.todolist.databinding.ItemTaskBinding
import br.com.isoftware.todolist.model.Task

class TaskListAdapter: ListAdapter<Task, TaskListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task) {
            binding.testviewTitle.text = item.title
            binding.testviewDate.text = "${item.date} ${item.hour}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class DiffCallback : DiffUtil.ItemCallback<Task>(){

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
}
