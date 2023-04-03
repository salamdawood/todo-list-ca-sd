package com.example.todolistcasd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(TodoItem::class), version = 1, exportSchema = false)
abstract class TodoItemRoomDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao

    private class TodoListDatabaseCallback(private val scope: CoroutineScope) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.todoItemDao())
                }
            }
        }

        suspend fun populateDatabase(todoItemDao: TodoItemDao) {
            //Add test items
            var todoItem = TodoItem("TEST")
            todoItemDao.insert(todoItem)
        }
    }

    /**
     * Here we use the Singleton pattern to create a single INSTANCE of a Room Database
     * @Volatile is used to indicate that the INSTANCE variable can have its value changed
     * from multiple threads. This annotation is used to ensure the value is always up to date.
     */
    companion object {
        @Volatile
        private var INSTANCE: TodoItemRoomDatabase? = null

        /**
         * If the INSTANCE is not null, then return it
         * if it is, then create the database
         */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TodoItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoItemRoomDatabase::class.java,
                    "todo_item_database"
                )
                    .addCallback(TodoListDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}