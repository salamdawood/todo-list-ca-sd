package com.example.todolistcasd

import androidx.room.Entity

/**
 * Explanation of TodoItem Data Class
 * @Entity(tableName = "todo_item_table") represents a SQLite table.
 * @PrimayKey assigned to the UUID of each row
 */
@Entity(tableName = "todo_item_table")
data class TodoItem(val item: String)
