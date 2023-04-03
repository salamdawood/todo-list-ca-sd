package com.example.todolistcasd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistcasd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val todoListViewModel: TodoListViewModel by viewModels {
        TodoListViewModelFactory((application as TodoListApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TodoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoListViewModel.allTodoItems.observe(this) { todoItems ->
            todoItems.let { adapter.submitList(it) }
        }

        binding.addButton.setOnClickListener {
            val newItem = TodoItem(binding.todoEditText.text.toString().trim())
            if (newItem.item.isNotEmpty()) {
                todoListViewModel.insert(newItem)
                binding.todoEditText.text.clear()
            }
        }
    }
}