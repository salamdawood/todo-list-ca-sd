package com.example.todolistcasd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * The purpose of this repository is to manage all queries and allows the use of multiple backends
 */
class TodoItemRepository(private val todoItemDao: TodoItemDao) {

    //Observed Flow will notify the observer when the data has changed
    val allTodoItems: Flow<List<TodoItem>> = todoItemDao.getAllTodoItems()

    @WorkerThread
    suspend fun insert(todoItem: TodoItem) {
        todoItemDao.insert(todoItem)
    }
}