package com.example.todolistcasd

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TodoItemDao {

    @Query("SELECT * FROM todo_item_table")
    fun getAllTodoItems(): List<TodoItem>
}