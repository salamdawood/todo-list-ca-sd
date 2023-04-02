package com.example.todolistcasd

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Explanation of TodoItem Data Class
 * @Entity(tableName = "todo_item_table") represents a SQLite table.
 * @PrimayKey assigned to the UUID of each row
 */
@Entity(tableName = "todo_item_table")
data class TodoItem(
    @PrimaryKey
    val id: UUID,
    val item: String,
    val dateAdded: String
)
