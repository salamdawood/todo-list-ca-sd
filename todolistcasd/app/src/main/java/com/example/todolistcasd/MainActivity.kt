package com.example.todolistcasd

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
            } else {
                itemOptionsAlertDialog()
            }
        }
    }

    fun itemOptionsAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("Update or Delete")
            .setMessage("Please select Delete or Update")
            .setPositiveButton("DELETE") { _, _ ->
                Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Update") { _, _ ->
                Toast.makeText(this, "UPDATED", Toast.LENGTH_SHORT).show()
            }
            .setCancelable(true)

        val dialog = builder.create()
        dialog.show()
    }
}