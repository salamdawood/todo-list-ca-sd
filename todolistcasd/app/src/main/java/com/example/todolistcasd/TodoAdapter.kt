package com.example.todolistcasd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter : ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(TodoItemComparator()) {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val todoItemView: TextView = itemView.findViewById(R.id.tv_list_item)

        fun bind(text: String?) {
            todoItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return TodoViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.item)

        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, current.item, Toast.LENGTH_SHORT).show()
        }
    }

    class TodoItemComparator : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.item == newItem.item
        }
    }
}
