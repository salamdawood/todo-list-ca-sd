package com.example.todolistcasd

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodoItemRepository): ViewModel() {

    /**
     * Here we use LiveData and cache what allTodoItems returns. By doing so we can
     * put an observer on the data and only update the UI when the data has actually changed.
     * We can also keep the Repository completely separate from the UI through the ViewModel
     */
    val allTodoItems: LiveData<List<TodoItem>> = repository.allTodoItems.asLiveData()

    fun insert(todoItem: TodoItem) = viewModelScope.launch {
        repository.insert(todoItem)
    }

    fun delete(todoItem: TodoItem) = viewModelScope.launch {
        repository.delete(todoItem)
    }
}

class TodoListViewModelFactory(private val repository: TodoItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}