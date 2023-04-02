package com.example.todolistcasd

import android.app.Application

/**
 * Make the Database and Repository members of the application class. The reason for this is:
 * 1. To ensure there is only one repository and database
 * 2. To increase performance by storing the repository and database on the device so they can
 * be retrieved when needed rather than constructed every time
 */
class TodoListApplication : Application() {
    /**
     * Using by lazy so the database and repository are only created when needed rather than
     * when the application starts
     */
    private val database by lazy { TodoItemRoomDatabase.getDatabase(this) }
    val repository by lazy { TodoItemRepository(database.todoItemDao())}
}