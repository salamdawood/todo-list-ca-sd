package com.example.todolistcasd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistcasd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val todoList = mutableListOf<String>()
    private val todoAdapter = TodoAdapter(todoList);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }


        binding.addButton.setOnClickListener {
            val newItem = binding.todoEditText.text.toString().trim()
            if (newItem.isNotEmpty()) {
                todoList.add(newItem)
                todoAdapter.notifyItemInserted(todoList.size - 1)
                binding.todoEditText.text.clear()
            }
        }
    }
}